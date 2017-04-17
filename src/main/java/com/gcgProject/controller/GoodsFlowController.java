package com.gcgProject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.gcgProject.entity.dto.GoodsFlowDto;
import com.gcgProject.service.GoodsFlowService;
import com.gcgProject.util.PageResults;

/**
 * 货物流水表
 * @author gcg
 * @date 2017-02-28 28:59:09
 */
@Controller
@RequestMapping("/information/goodsFlow")
public class GoodsFlowController extends BaseController {

	@Autowired
	private GoodsFlowService goodsFlowService;
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/information/goodsFlow");
		return mv;
	}
	
	/**
	 * 货物流水列表
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public JSONObject list(PageResults<GoodsFlowDto> page, String searchData) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", page.getStart());
		map.put("b", page.getEnd());
		map.put(GoodsFlowDto.FIELD_GOODS_NAME, searchData);
		this.goodsFlowService.findPage(page, map);
		result.put("paging", page);
		return result;
	}
	
}