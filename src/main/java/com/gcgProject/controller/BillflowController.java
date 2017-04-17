package com.gcgProject.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.gcgProject.entity.Billflow;
import com.gcgProject.service.BillflowService;
import com.gcgProject.util.PageResults;

/**
 * 账单流水表
 * @author gcg
 * @date 2017-03-07 07:56:58
 */
@Controller
@RequestMapping("/information/billflow")
public class BillflowController extends BaseController {

	@Autowired
	private BillflowService billflowService;
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		BigDecimal gathering = this.billflowService.getCountBillFlow(Billflow.GATHERING);
		BigDecimal refund = this.billflowService.getCountBillFlow(Billflow.REFUND);
		mv.addObject("gathering", gathering);
		mv.addObject("refund", refund.multiply(new BigDecimal(-1)));
		mv.setViewName("/information/billFlow");
		return mv;
	}
	
	/**
	 * 账单列表
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public JSONObject list(PageResults<Billflow> page, String searchData) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", page.getStart());
		map.put("b", page.getEnd());
		this.billflowService.findPage(page, map);
		result.put("paging", page);
		return result;
	}
	
}