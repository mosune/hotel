package com.gcgProject.controller;

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
import com.gcgProject.entity.Room;
import com.gcgProject.entity.Roomtype;
import com.gcgProject.service.RoomService;
import com.gcgProject.service.RoomtypeService;
import com.gcgProject.util.PageResults;

/**
 * 房间类型表
 * @author gcg
 * @date 2017-02-25 25:12:40
 */
@Controller
@RequestMapping("/hotel/roomtype")
public class RoomtypeController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(RoomtypeController.class);

	@Autowired
	private RoomtypeService roomtypeService;
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/roomtype/index");
		return mv;
	}
	
	/**
	 * 房间类型列表
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public JSONObject list(PageResults<Roomtype> page, String searchData) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", page.getStart());
		map.put("b", page.getEnd());
		map.put(Roomtype.FIELD_NAME, searchData);
		map.put(Roomtype.FIELD_REMARK, searchData);
		this.roomtypeService.findPage(page, map);
		result.put("paging", page);
		return result;
	}
	
	/**
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping("/toAdd.do")
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/roomtype/add");
		return mv;
	}
	
	/**
	 * 添加员工
	 * @param staff 员工
	 * @return
	 */
	@RequestMapping("/addOrUpdate.do")
	public ModelAndView addOrUpdate(Roomtype roomtype, String flag) {
		ModelAndView mv = new ModelAndView();
		if (roomtype == null) return mv;
		if (flag.equals("1")) {
			Roomtype newRoomtype = this.roomtypeService.get(roomtype);
			if (roomtype.getName() != null) newRoomtype.setName(roomtype.getName());
			if (roomtype.getRemark() != null) newRoomtype.setRemark(roomtype.getRemark());
			this.roomtypeService.update(newRoomtype);
			logger.info("{}修改{}房间类型信息", getUserRealName(), roomtype.getName());
			mv.addObject("success", "2");
		} else {
			this.roomtypeService.save(roomtype);
			logger.info("{}保存{}房间类型", getUserRealName(), roomtype.getName());
			mv.addObject("success", "1");
		}
		mv.setViewName("/roomtype/index");
		return mv;
	}
	
	/**
	 * 跳转更新页面
	 * @param staff
	 * @return
	 */
	@RequestMapping("/toUpdate.do")
	public ModelAndView toUpdate(Roomtype roomtype) {
		ModelAndView mv = new ModelAndView();
		if (roomtype.getId() == null) return mv;
		roomtype = this.roomtypeService.get(roomtype);
		mv.addObject("flag", 1);
		mv.addObject("roomtype", roomtype);
		mv.setViewName("/roomtype/add");
		return mv;
	}
	
	/**
	 * 查询是否包含房间
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findRoom.do")
	public JSONObject findRoom(Roomtype roomtype) {
		JSONObject result = new JSONObject();
		if (roomtype.getId() == null) return result;
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put(Room.FIELD_TYPE_ID, roomtype.getId());
		List<Room> room = this.roomService.find(maps);
		if (room.size() > 0) result.put("flag", 0);
		else result.put("flag", 1);
		return result;
	}
	
	/**
	 * 删除房间类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteRoomType.do")
	public JSONObject deleteRoomType(Roomtype roomtype) {
		JSONObject result = new JSONObject();
		if (roomtype.getId() == null) return result;
		roomtype = this.roomtypeService.get(roomtype);
		this.roomtypeService.delete(roomtype);
		logger.info("{}删除{}房间类型", getUserRealName(), roomtype.getName());
		return result;
	}
	
}