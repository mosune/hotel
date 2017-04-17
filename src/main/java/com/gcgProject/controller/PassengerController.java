package com.gcgProject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.gcgProject.entity.Passenger;
import com.gcgProject.service.PassengerService;
import com.gcgProject.util.PageResults;

/**
 * 旅客表
 * @author gcg
 * @date 2017-03-01 01:11:00
 */
@Controller
@RequestMapping("/information/passenger")
public class PassengerController extends BaseController {

	@Autowired
	private PassengerService passengerService;
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/information/passenger");
		return mv;
	}
	
	/**
	 * 旅客列表
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public JSONObject list(PageResults<Passenger> page, String searchData) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", page.getStart());
		map.put("b", page.getEnd());
		map.put(Passenger.FIELD_ID_CODE, searchData);
		map.put(Passenger.FIELD_NAME, searchData);
		this.passengerService.findPage(page, map);
		result.put("paging", page);
		return result;
	}

}