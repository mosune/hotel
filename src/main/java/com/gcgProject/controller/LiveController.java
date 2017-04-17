package com.gcgProject.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.gcgProject.entity.Billflow;
import com.gcgProject.entity.Live;
import com.gcgProject.entity.Passenger;
import com.gcgProject.entity.Room;
import com.gcgProject.entity.Roomtype;
import com.gcgProject.entity.dto.RoomTypeDto;
import com.gcgProject.entity.dto.LiveDto;
import com.gcgProject.entity.dto.RoomDto;
import com.gcgProject.service.LiveService;
import com.gcgProject.service.PassengerService;
import com.gcgProject.service.RoomService;
import com.gcgProject.service.RoomtypeService;
import com.gcgProject.util.IDUtils;
import com.gcgProject.util.PageResults;

/**
 * 住客表
 * @author gcg
 * @date 2017-03-01 01:29:45
 */
@Controller
@RequestMapping("/living/live")
public class LiveController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(LiveController.class);  

	@Autowired
	private LiveService liveService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RoomtypeService roomtypeService;
	
	@Autowired
	private PassengerService passengerService;
	
	/**
	 * 跳转首页面
	 * @return
	 */
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		int livingNum = this.roomService.countRoomNum(0);
		int livedNum = this.roomService.countRoomNum(1);
		int scheduleNum = this.roomService.countRoomNum(2);
		mv.addObject("livingNum", livingNum);
		mv.addObject("livedNum", livedNum);
		mv.addObject("scheduleNum", scheduleNum);
		mv.setViewName("/live/index");
		return mv;
	}
	
	/**
	 * 跳转选择房间类型界面
	 * @return
	 */
	@RequestMapping("/chooseType.do")
	public ModelAndView chooseType() {
		ModelAndView mv = new ModelAndView();
		List<Roomtype> lists = this.roomtypeService.find(null);
		List<RoomTypeDto> list = new ArrayList<RoomTypeDto>();
		for (Roomtype roomtype : lists) {
			int count = this.roomtypeService.queryCount(roomtype.getId(), 0);
			RoomTypeDto roomtypeDto = new RoomTypeDto();
			roomtypeDto.setId(roomtype.getId());
			roomtypeDto.setName(roomtype.getName());
			roomtypeDto.setRemark(roomtype.getRemark());
			roomtypeDto.setCount(count);
			list.add(roomtypeDto);
		}
		mv.addObject("roomtypes", list);
		mv.setViewName("/live/chooseType");
		return mv;
	}
	
	/**
	 * 跳转选择房间页面
	 * @return
	 */
	@RequestMapping("/toChooseRoom.do")
	public ModelAndView toChooseRoom(String id) {
		ModelAndView mv = new ModelAndView();
		if (id == null) return mv;
		mv.addObject("typeId", id);
		mv.addObject("flag", 0);
		mv.setViewName("/live/chooseRoom");
		return mv;
	}
	
	/**
	 * 跳转入住页面
	 * @return
	 */
	@RequestMapping("/toLiving.do")
	public ModelAndView toLiving(String id, String typeId) {
		ModelAndView mv = new ModelAndView();
		if (id == null) return mv;
		if (typeId == null) return mv;
		Room room = new Room();
		room.setId(id);
		room = this.roomService.get(room);
		mv.addObject("room", room);
		mv.addObject("typeId", typeId);
		mv.setViewName("/live/living");
		return mv;
	}
	
	/**
	 * 检验是否为vip
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("/checkVip.do")
	public JSONObject checkVip(String custName, String idNum) {
		JSONObject result = new JSONObject();
		if (custName == null) return result;
		if (idNum == null) return result;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Passenger.FIELD_NAME, custName);
		map.put(Passenger.FIELD_ID_CODE, Integer.valueOf(idNum));
		List<Passenger> passengers = this.passengerService.find(map);
		if (passengers.size() != 0) {
			Passenger passenger = passengers.get(0);
			if (passenger.getVipId() != null) result.put("flag", 1);
			else result.put("flag", 0);
		} 
		else result.put("flag", 0);
		return result;
	}*/
	
	/**
	 * 办理入住
	 * @param live
	 * @param vipflag
	 * @param chargeStr
	 * @param depositStr
	 * @param custName
	 * @param custIdNum
	 * @return
	 */
	@RequestMapping("/living.do")
	public ModelAndView living(Live live, String vipflag, String chargeStr, String depositStr, String custName, String custIdNum) {
		ModelAndView mv = new ModelAndView();
		if (custName == null) return mv;
		if (custIdNum == null) return mv;
		if (live == null) return mv;
		if (vipflag == null) return mv;
		if (chargeStr == null) return mv;
		if (depositStr == null) return mv;
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put(Passenger.FIELD_NAME, custName);
		maps.put(Passenger.FIELD_ID_CODE, custIdNum);
		List<Passenger> lists = this.passengerService.find(maps);
		Passenger passenger = null;
		if (lists.size() != 0) {
			live.setCustId(lists.get(0).getId());
		} else {
			passenger = new Passenger();
			passenger.setId(IDUtils.getUUID());
			passenger.setIdCode(custIdNum);
			passenger.setName(custName);
			live.setCustId(passenger.getId());
		}
		live.setCharge(new BigDecimal(chargeStr));
		live.setDeposit(new BigDecimal(depositStr));
		live.setId(IDUtils.getUUID());
		live.setCreateTime(new Date());
		Billflow billFlow = null;
		if (live.getDestineTime().equals("0")) {
			billFlow = new Billflow();
			billFlow.setId(IDUtils.getUUID());
			billFlow.setCharge(new BigDecimal(chargeStr));
			billFlow.setType(Billflow.GATHERING);
			billFlow.setCreateTime(new Date());
		}
		maps.clear();
		Room room = new Room();
		room.setId(live.getRoomId());
		room = this.roomService.get(room);
		if (live.getDestineTime().equals("0")) room.setFlag("1");
		else room.setFlag("2");
		this.liveService.saveAndUpdatePass(live, passenger, billFlow, room);
		logger.info("{}办理了{}的入住，并收款{}元，其中押金{}元", getUserRealName(), custName, chargeStr, depositStr);
		return new ModelAndView("redirect://living/live/index.do");
	}
	
	/**
	 * 跳转结账页面
	 * @return
	 */
	@RequestMapping("/listsLiving.do")
	public ModelAndView settleLists(String type) {
		ModelAndView mv = new ModelAndView();
		if(type.equals("1")) mv.setViewName("/live/settleLists");
		if(type.equals("2")) mv.setViewName("/live/orderList");
		return mv;
	}
	
	/**
	 * 预定列表
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/settleList.do")
	public JSONObject settleList(PageResults<LiveDto> page, String searchData, String flag) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", page.getStart());
		map.put("b", page.getEnd());
		map.put(Room.FIELD_ROOM_NUM, searchData);
		map.put(Passenger.FIELD_NAME, searchData);
		map.put(Room.FIELD_FLAG, flag);
		this.liveService.findPageSettle(page, map);
		result.put("paging", page);
		return result;
	}
	
	/**
	 * 预订房间入住
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/livingOrder.do")
	public JSONObject livingOrder(Live live) {
		JSONObject result = new JSONObject();
		if (live.getId() == null) return result;
		if (live.getCharge() == null) return result;
		if (live.getLiveTime() == null) return result;
		if (live.getEndTime() == null) return result;
		if (live.getDeposit() == null) return result;
		Live oldLive = this.liveService.get(live);
		if (live.getCharge() != null) oldLive.setCharge(live.getCharge());
		if (live.getLiveTime() != null) oldLive.setLiveTime(live.getLiveTime());
		if (live.getEndTime() != null) oldLive.setEndTime(live.getEndTime());
		if (live.getDeposit() != null) oldLive.setDeposit(live.getDeposit());
		oldLive.setDestineTime("0");
		Room room = new Room();
		room.setId(oldLive.getRoomId());
		room = this.roomService.get(room);
		room.setFlag("1");
		this.liveService.updateLiveAndRoom(oldLive, room);
		logger.info("{}办理了预订房{}入住，并收款{}元，其中押金{}元", getUserRealName(), room.getRoomNum(), live.getCharge(), live.getDeposit());
		return result;
	}
	
	/**
	 * 获取入住记录
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getLiving.do")
	public JSONObject getLiving(Live live) {
		JSONObject result = new JSONObject();
		if (live.getId() == null) return result;
		live = this.liveService.get(live);
		result.put("live", live);
		return result;
	}
	
	/**
	 * 取消预订
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/cancelOrder.do")
	public JSONObject cancelOrder(Live live) {
		JSONObject result = new JSONObject();
		if (live.getId() == null) return result;
		live.setRealEndTime(new Date());
		live = this.liveService.get(live);
		Room room = new Room();
		room.setId(live.getRoomId());
		room = this.roomService.get(room);
		room.setFlag("0");
		this.liveService.updateLiveAndRoom(live, room);
		logger.info("{}取消了{}的预定", getUserRealName(), room.getRoomNum());
		return result;
	}
	
	/**
	 * 结账
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping("/settleRoom.do")
	public JSONObject settleRoom(String id, String realTime, String price) throws ParseException {
		JSONObject result = new JSONObject();
		if (id == null) return result;
		if (realTime == null) return result;
		if (price == null) return result;
		Live live = new Live();
		live.setId(id);
		live = this.liveService.get(live);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(realTime);
		live.setRealEndTime(date);
		Room room = new Room();
		room.setId(live.getRoomId());
		room = this.roomService.get(room);
		room.setFlag("0");
		Billflow billflow = new Billflow();
		billflow.setId(IDUtils.getUUID());
		BigDecimal charge = new BigDecimal(price);
		if (charge.compareTo(BigDecimal.ZERO) == 1) billflow.setType(Billflow.GATHERING);
		else if (charge.compareTo(BigDecimal.ZERO) == -1) billflow.setType(Billflow.REFUND);
		billflow.setCharge(charge);
		billflow.setCreateTime(new Date());
		this.liveService.updateSettleRoom(live, room, billflow);
		if (charge.compareTo(BigDecimal.ZERO) == 1) logger.info("{}办理了{}的结账,收费{}", getUserRealName(), room.getRoomNum(), charge);
		else if (charge.compareTo(BigDecimal.ZERO) == -1) logger.info("{}办理了{}的结账，退款{}", getUserRealName(), room.getRoomNum(), charge);
		return result;
	}
	
	/**
	 * 续房
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping("/continueRoom.do")
	public JSONObject continueRoom(Live live, String price, String endTimeStr) throws ParseException {
		JSONObject result = new JSONObject();
		if (live.getId() == null) return result;
		if (endTimeStr == null) return result;
		if (live.getDeposit() == null) return result;
		if (price == null) return result;
		Live newLive = new Live();
		live.setId(live.getId());
		newLive = this.liveService.get(live);
		BigDecimal realPrice = new BigDecimal(price);
		newLive.setCharge(newLive.getCharge().add(realPrice));
		newLive.setDeposit(newLive.getDeposit().add(live.getDeposit()));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date endTime = sf.parse(endTimeStr);
		newLive.setEndTime(endTime);
		Billflow billflow = new Billflow();
		billflow.setId(IDUtils.getUUID());
		BigDecimal charge = new BigDecimal(price);
		if (charge.compareTo(BigDecimal.ZERO) == 1) billflow.setType(Billflow.GATHERING);
		if (charge.compareTo(BigDecimal.ZERO) == -1) billflow.setType(Billflow.REFUND);
		billflow.setCharge(charge);
		billflow.setCreateTime(new Date());
		this.liveService.updateContinueRoom(newLive, billflow);
		if (charge.compareTo(BigDecimal.ZERO) == 1) logger.info("{}办理了续房,收费{}", getUserRealName(), charge);
		else if (charge.compareTo(BigDecimal.ZERO) == -1) logger.info("{}办理了续房，退款{}", getUserRealName(), charge);
		return result;
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
	
}