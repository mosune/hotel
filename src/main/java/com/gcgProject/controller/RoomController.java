package com.gcgProject.controller;

import java.math.BigDecimal;
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
import com.gcgProject.entity.Room;
import com.gcgProject.entity.Roomtype;
import com.gcgProject.entity.dto.RoomDto;
import com.gcgProject.service.RoomService;
import com.gcgProject.service.RoomtypeService;
import com.gcgProject.util.IDUtils;
import com.gcgProject.util.PageResults;

/**
 * 房间表
 * @author gcg
 * @date 2017-02-27 27:00:19
 */
@Controller
@RequestMapping("/hotel/room")
public class RoomController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomtypeService roomtypeService;
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/room/index");
		return mv;
	}
	
	/**
	 * 跳转添加页面
	 * @return
	 */
	@RequestMapping("/toAddOrUpdate.do")
	public ModelAndView toAddOrUpdate(String id) {
		ModelAndView mv = new ModelAndView();
		if (id != null) {
			Room room = new Room();
			room.setId(id);
			room = this.roomService.get(room);
			mv.addObject("flag", 1);
			mv.addObject("room", room);
		}
		List<Roomtype> roomTypes = this.roomtypeService.find(null);
		mv.addObject("roomTypes", roomTypes);
		mv.setViewName("/room/add");
		return mv;
	}
	
	/**
	 * 添加员工
	 * @param staff 员工
	 * @return
	 */
	@RequestMapping("/addOrUpdate.do")
	public ModelAndView addOrUpdate(Room room, String flag, String priceStr) {
		ModelAndView mv = new ModelAndView();
		if (room == null) return mv;
		if (flag.equals("1")) {
			Room newRoom = this.roomService.get(room);
			if (room.getRoomNum() != null) newRoom.setRoomNum(room.getRoomNum());
			if (priceStr != null) newRoom.setPrice(new BigDecimal(priceStr));
			if (room.getRemark() != null) newRoom.setRemark(room.getRemark());
			newRoom.setTvFlag(room.getTvFlag());
			newRoom.setAirconFlag(room.getAirconFlag());
			newRoom.setShowerFlag(room.getShowerFlag());
			newRoom.setWindowFlag(room.getWindowFlag());
			this.roomService.update(newRoom);
			logger.info("{}修改{}房间信息", getUserRealName(), room.getRoomNum());
			mv.addObject("addSuccess", "2");
		} else {
			room.setId(IDUtils.getUUID());
			room.setCreateTime(new Date());
			room.setPrice(new BigDecimal(priceStr));
			room.setFlag("0");
			this.roomService.save(room);
			logger.info("{}添加{}房间", getUserRealName(), room.getRoomNum());
			mv.addObject("addSuccess", "1");
		}
		mv.setViewName("/room/index");
		return mv;
	}
	
	/**
	 * 房间列表
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public JSONObject list(PageResults<RoomDto> page, String searchData, String typeId, String flag) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", page.getStart());
		map.put("b", page.getEnd());
		map.put(Room.FIELD_ROOM_NUM, searchData);
		map.put(Roomtype.FIELD_NAME, searchData);
		map.put(Room.FIELD_REMARK, searchData);
		if (typeId != null && flag != null) {
			map.put(Room.FIELD_TYPE_ID, typeId);
			map.put(Room.FIELD_FLAG, flag);
		}
		this.roomService.findPage(page, map);
		result.put("paging", page);
		return result;
	}
	
	/**
	 * 删除房间
	 * @param staff
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteRoom.do")
	public JSONObject deleteStaff(Room room) {
		JSONObject result = new JSONObject();
		if (room.getId() == null) return result;
		this.roomService.delete(room);
		logger.info("{}删除{}房间", getUserRealName(), room.getRoomNum());
		return result;
	}
	
}