package com.gcgProject.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.gcgProject.entity.Menu;
import com.gcgProject.entity.PosMenuReltion;
import com.gcgProject.entity.Position;
import com.gcgProject.entity.Staff;
import com.gcgProject.service.MenuService;
import com.gcgProject.service.PosMenuReltionService;
import com.gcgProject.service.PositionService;
import com.gcgProject.service.StaffService;
import com.gcgProject.util.PageResults;

/**
 * 职位表
 * @author gcg
 * @date 2017-03-16 16:26:48
 */
@Controller
@RequestMapping("/system/position")
public class PositionController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(PositionController.class);

	@Autowired
	private PositionService positionService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private PosMenuReltionService posMenuReltionService;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		List<Menu> menus = this.menuService.findFristMenus();
		mv.addObject("menus", menus);
		mv.setViewName("/position/index");
		return mv;
	}
	
	/**
	 * 职位列表
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public JSONObject list(PageResults<Position> page, String searchData) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", page.getStart());
		map.put("b", page.getEnd());
		map.put(Position.FIELD_NAME, searchData);
		this.positionService.findPage(page, map);
		result.put("paging", page);
		return result;
	}
	
	/**
	 * 编辑职位信息
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changeName.do")
	public JSONObject changeName(Position position) {
		JSONObject result = new JSONObject();
		if (position.getId() == null) return result;
		if (position.getName() == null) return result;
		this.positionService.update(position);
		logger.info("{}修改{}职位信息", getUserRealName(), position.getName());
		return result;
	}
	
	/**
	 * 查询是否可以进行删除
	 */
	@ResponseBody
	@RequestMapping("/checkStaff.do")
	public JSONObject checkStaff(Position position) {
		JSONObject result = new JSONObject();
		if (position.getId() == null) return result;
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put(Staff.FIELD_POSITION_ID, position.getId());
		List<Staff> staffs = this.staffService.find(maps);
		if (staffs.size() > 0) result.put("flag", 0);
		else result.put("flag", 1);
		return result;
	}
	
	/**
	 * 查询是否可以进行删除
	 */
	@ResponseBody
	@RequestMapping("/deletePosition.do")
	public JSONObject deletePosition(Position position) {
		JSONObject result = new JSONObject();
		if (position.getId() == null) return result;
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put(PosMenuReltion.FIELD_POS_ID, position.getId());
		List<PosMenuReltion> posMenuReltions = this.posMenuReltionService.find(maps);
		PosMenuReltion posMenuReltion = null;
		if (posMenuReltions.size() > 0) {
			posMenuReltion = new PosMenuReltion();
			posMenuReltion.setPosId(position.getId());
		}
		this.positionService.deletePosAndRelation(position, posMenuReltion);
		logger.info("{}删除{}职位", getUserRealName(), position.getName());
		return result;
	}
	
	/**
	 * 查询是否可以进行删除
	 */
	@ResponseBody
	@RequestMapping("/getPowerList.do")
	public JSONObject getPowerList(Position position) {
		JSONObject result = new JSONObject();
		if (position.getId() == null) return result;
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put(PosMenuReltion.FIELD_POS_ID, position.getId());
		List<PosMenuReltion> posMenuReltions = this.posMenuReltionService.find(maps);
		result.put("posMenuReltions", posMenuReltions);
		return result;
	}
	
	/**
	 * 查询是否可以进行删除
	 */
	@ResponseBody
	@RequestMapping("/changePower.do")
	public JSONObject changePower(Position position, String menus) {
		JSONObject result = new JSONObject();
		if (position.getId() == null) return result;
		if (menus == null) return result;
		Map<String, Object> maps = new HashMap<String, Object>();
		if (menus.length() > 0) {
			menus = menus.substring(1, menus.length());
			List<PosMenuReltion> posMenuReltions = new ArrayList<PosMenuReltion>();
			String[] menu = menus.split(",");
			for (int i = 0; i < menu.length; i++) {
				PosMenuReltion posMenuReltion = new PosMenuReltion();
				posMenuReltion.setPosId(position.getId());
				posMenuReltion.setMenuId(Integer.valueOf(menu[i]));
				posMenuReltions.add(posMenuReltion);
			}
			maps.put(PosMenuReltion.FIELD_POS_ID, position.getId());
			List<PosMenuReltion> lists = this.posMenuReltionService.find(maps);
			this.positionService.changePower(posMenuReltions, lists);
			logger.info("{}修改{}职位权限", getUserRealName(), position.getName());
		} else {
			maps.put(PosMenuReltion.FIELD_POS_ID, position.getId());
			List<PosMenuReltion> lists = this.posMenuReltionService.find(maps);
			if (lists.size() > 0) {
				PosMenuReltion posMenuReltion = new PosMenuReltion();
				posMenuReltion.setPosId(position.getId());
				this.posMenuReltionService.delete(posMenuReltion);
				logger.info("{}删除{}职位所有权限", getUserRealName(), position.getName());
			}
		}
		return result;
	}
	
	/**
	 * 添加职位
	 */
	@ResponseBody
	@RequestMapping("/addPosition.do")
	public JSONObject addPosition(Position position) {
		JSONObject result = new JSONObject();
		if (position.getName() == null) return result;
		this.positionService.save(position);
		logger.info("{}添加{}职位", getUserRealName(), position.getName());
		return result;
	}
	
}