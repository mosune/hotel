package com.gcgProject.controller;

import java.util.Date;
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
import com.gcgProject.controller.BaseController;
import com.gcgProject.entity.Position;
import com.gcgProject.entity.Staff;
import com.gcgProject.entity.dto.StaffDto;
import com.gcgProject.service.PositionService;
import com.gcgProject.service.StaffService;
import com.gcgProject.util.IDUtils;
import com.gcgProject.util.MD5Util;
import com.gcgProject.util.PageResults;

/**
 * 员工表
 * @author Lion.z
 * @date 2017-02-18 18:20:10
 */
@Controller
@RequestMapping("/system/staff")
public class StaffController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(StaffController.class);  

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private PositionService positionService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/staff/index");
		return mv;
	}
	
	/**
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping("/toAdd.do")
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView();
		List<Position> positions = this.positionService.findPosition();
		mv.addObject("positions", positions);
		mv.setViewName("/staff/add");
		return mv;
	}
	
	/**
	 * 添加员工
	 * @param staff 员工
	 * @return
	 */
	@RequestMapping("/addOrUpdate.do")
	public ModelAndView addOrUpdate(Staff staff, String flag) {
		ModelAndView mv = new ModelAndView();
		if (staff == null) return mv;
		if (flag.equals("1")) {
			Staff newStaff = this.staffService.get(staff);
			if (staff.getLoginName() != null) newStaff.setLoginName(staff.getLoginName());
			if (staff.getRealName() != null) newStaff.setRealName(staff.getRealName());
			if (staff.getPositionId() != null) newStaff.setPositionId(staff.getPositionId());
			if (staff.getRemark() != null) newStaff.setRemark(staff.getRemark());
			this.staffService.update(newStaff);
			logger.info("{}修改了{}的信息", getUserRealName(), staff.getRealName());
			mv.addObject("addSuccess", "2");
		} else {
			staff.setId(IDUtils.getUUID());
			if (staff.getPassword() == null) staff.setPassword(MD5Util.MD5("123456"));
			else staff.setPassword(MD5Util.MD5(staff.getPassword()));
			staff.setFlag(1);
			staff.setCreateTime(new Date());
			this.staffService.save(staff);
			logger.info("{}添加了{}的信息", getUserRealName(), staff.getRealName());
			mv.addObject("addSuccess", "1");
		}
		mv.setViewName("/staff/index");
		return mv;
	}
	
	/**
	 * 改变员工状态
	 * @param staff
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changeFlag.do")
	public JSONObject changeFlag(Staff staff) {
		JSONObject result = new JSONObject();
		if (staff.getId() == null) return result;
		staff = this.staffService.get(staff);
		if (staff.getFlag() == 1) staff.setFlag(0);
		else staff.setFlag(1);
		this.staffService.update(staff);
		logger.info("{}改变了{}的状态", getUserRealName(), staff.getRealName());
		return result;
	}
	
	/**
	 * 跳转更新页面
	 * @param staff
	 * @return
	 */
	@RequestMapping("/toUpdate.do")
	public ModelAndView toUpdate(Staff staff) {
		ModelAndView mv = new ModelAndView();
		if (staff.getId() == null) return mv;
		staff = this.staffService.get(staff);
		List<Position> positions = this.positionService.findPosition();
		mv.addObject("positions", positions);
		mv.addObject("flag", 1);
		mv.addObject("staff", staff);
		mv.setViewName("/staff/add");
		return mv;
	}
	
	/**
	 * 删除员工
	 * @param staff
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteStaff.do")
	public JSONObject deleteStaff(Staff staff) {
		JSONObject result = new JSONObject();
		if (staff.getId() == null) return result;
		this.staffService.delete(staff);
		logger.info("{}删除了{}", getUserRealName(), staff.getRealName());
		return result;
	}
	
	/**
	 * 检查用户名是否已有
	 * @param staff
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkLoginName.do")
	public JSONObject checkLoginName(String loginName) {
		JSONObject result = new JSONObject();
		if (loginName == null) return result;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Staff.FIELD_LOGIN_NAME, loginName);
		List<Staff> list = this.staffService.find(map);
		if (list.size() != 0) result.put("flag", 1);
		else result.put("flag", 0);
		return result;
	}
	
	/**
	 * 员工列表
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public JSONObject list(PageResults<StaffDto> page, String searchData) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", page.getStart());
		map.put("b", page.getEnd());
		map.put(Staff.FIELD_LOGIN_NAME, searchData);
		map.put(Staff.FIELD_REAL_NAME, searchData);
		map.put(Staff.FIELD_REMARK, searchData);
		this.staffService.findPage(page, map);
		result.put("paging", page);
		return result;
	}
	
}