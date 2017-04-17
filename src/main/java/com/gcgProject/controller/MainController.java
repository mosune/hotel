package com.gcgProject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.gcgProject.entity.Live;
import com.gcgProject.entity.Menu;
import com.gcgProject.entity.Room;
import com.gcgProject.entity.Staff;
import com.gcgProject.entity.User;
import com.gcgProject.entity.dto.LiveDto;
import com.gcgProject.service.LiveService;
import com.gcgProject.service.PositionService;
import com.gcgProject.service.RoomService;
import com.gcgProject.service.StaffService;
import com.gcgProject.util.MD5Util;

/**
 * 主要操作控制器
 * @author Administrator
 *
 */
@Controller
public class MainController {
	
	private static Logger logger = LoggerFactory.getLogger(MainController.class);  
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private LiveService liveService;
	
	@Autowired
	private RoomService roomService;
    
	/**
	 * 跳转登录界面
	 * @return
	 */
    @RequestMapping("/toLogin.do")
    public ModelAndView toLogin() {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/login");
    	return mv;
    }
    
    /**
     * 跳转登录界面
     * @return
     */
    @RequestMapping("/toIndex.do")
    public ModelAndView toIndex() {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/index");
    	return mv;
    }

    /**
     * 登录
     * @param staff
     * @param session
     * @return
     */
	@RequestMapping("/login.do")
	protected ModelAndView login(Staff staff, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (staff.getLoginName() == null) {
			mv.addObject("tip", "请输入用户名！");
			mv.setViewName("/login");
            return mv;
		}
		if (staff.getPassword() == null) {
			mv.addObject("tip", "请输入密码！");
			mv.setViewName("/login");
			return mv;
		}
        Map<String, Object> map = new HashMap<String, Object>();
		map.put(Staff.FIELD_LOGIN_NAME, staff.getLoginName());
		List<Staff> staffs = this.staffService.find(map); 
		if (staffs.size() == 0) {
			mv.addObject("tip", "该账户不存在！");
			mv.setViewName("/login");
            return mv;
		}
		Staff staff1 = staffs.get(0);
		if (!MD5Util.checkPassword(staff.getPassword(), staff1.getPassword())) {
			mv.addObject("tip", "密码错误！");
			mv.setViewName("/login");
			return mv;
		}
        if(staff1.getFlag().equals("0")){  
        	mv.addObject("tip", "该账户已被冻结！");
        	mv.setViewName("/login");
            return mv;  
        }
        User user = new User();
        user.setId(staff1.getId());
        user.setLoginName(staff1.getLoginName());
        user.setPassword(staff1.getPassword());
        user.setPositionId(staff1.getPositionId());
        user.setRealName(staff1.getRealName());
        List<Menu> menus = this.positionService.getPosById(staff1.getPositionId());
        user.setMenus(menus);
        List<Menu> mainMenu = new ArrayList<Menu>();
        for (Menu menu : menus) {
			if (menu.getPid() == 0) {
				mainMenu.add(menu);
			}
		}
        user.setMainMenus(mainMenu);
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(60 * 60);
        logger.info("{}登录系统", user.getRealName());
		return new ModelAndView("redirect:/toIndex.do");
	}
	
	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginOut.do")
	public ModelAndView loginOut(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute("user");
		logger.info("{}注销", user.getRealName());
		session.invalidate();
		mv.setViewName("/login");
		return mv;
	}
	
	/**
	 * 修改密码
	 * @param session
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updatePwd.do")
	public JSONObject updatePwd(HttpSession session, String oldPassword, String newPassword) {
		JSONObject result = new JSONObject();
		if (oldPassword == null) return result;
		if (newPassword == null) return result;
		User user = (User) session.getAttribute("user");
		if (!MD5Util.checkPassword(oldPassword, user.getPassword())) {
			result.put("flag", 0);
			return result;
		}
		Staff staff = new Staff();
		staff.setId(user.getId());
		staff = this.staffService.get(staff);
		staff.setPassword(MD5Util.MD5(newPassword));
		this.staffService.update(staff);
		logger.info("{}修改密码", user.getRealName());
		result.put("flag", 1);
		return result;
	}
	
	/**
	 * 跳转首页面
	 * @return
	 */
	@RequestMapping("/toHome.do")
	public ModelAndView toHomepage() {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("destine_time", "0");
		map.put("flag", "1");
		List<LiveDto> liveSettleDtos = this.liveService.findRoomByFlag(map);
		map.put(Live.FIELD_DESTINE_TIME, "1");
		List<LiveDto> liveOrderDtos = this.liveService.findRoomByFlag(map);
		map.remove(Live.FIELD_DESTINE_TIME);
		map.put(Room.FIELD_FLAG, "0");
		List<Room> rooms = this.roomService.find(map);
		mv.addObject("liveSettleDtos", liveSettleDtos);
		mv.addObject("liveOrderDtos", liveOrderDtos);
		mv.addObject("rooms", rooms);
		mv.setViewName("homepage");
		return mv;
	}
	
}
