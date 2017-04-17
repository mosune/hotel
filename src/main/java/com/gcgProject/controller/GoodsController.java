package com.gcgProject.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.gcgProject.entity.Goods;
import com.gcgProject.entity.GoodsFlow;
import com.gcgProject.service.GoodsService;
import com.gcgProject.util.IDUtils;
import com.gcgProject.util.PageResults;

/**
 * 货物表
 * @author gcg
 * @date 2017-02-28 28:21:33
 */
@Controller
@RequestMapping("/hotel/goods")
public class GoodsController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(GoodsController.class); 

	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/goods/index");
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
			Goods goods = new Goods();
			goods.setId(id);
			goods = this.goodsService.get(goods);
			mv.addObject("flag", 1);
			mv.addObject("goods", goods);
		}
		mv.setViewName("/goods/add");
		return mv;
	}
	
	/**
	 * 添加货物
	 * @param staff 货物
	 * @return
	 */
	@RequestMapping("/addOrUpdate.do")
	public ModelAndView addOrUpdate(Goods goods, String flag) {
		ModelAndView mv = new ModelAndView();
		if (goods == null) return mv;
		if (flag.equals("1")) {
			Goods newGoods = this.goodsService.get(goods);
			if (goods.getGoodsName() != null) newGoods.setGoodsName(goods.getGoodsName());
			if (goods.getRemark() != null) newGoods.setRemark(goods.getRemark());
			this.goodsService.update(newGoods);
			logger.info("{}更新了货物{}的信息", getUserRealName(), goods.getGoodsName());
			mv.addObject("addSuccess", "2");
		} else {
			goods.setId(IDUtils.getUUID());
			goods.setAmount(0);
			this.goodsService.save(goods);
			logger.info("{}添加了货物{}", getUserRealName(), goods.getGoodsName());
			mv.addObject("addSuccess", "1");
		}
		mv.setViewName("/goods/index");
		return mv;
	}
	
	/**
	 * 货物列表
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public JSONObject list(PageResults<Goods> page, String searchData) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", page.getStart());
		map.put("b", page.getEnd());
		map.put(Goods.FIELD_GOODS_NAME, searchData);
		map.put(Goods.FIELD_REMARK, searchData);
		this.goodsService.findPage(page, map);
		result.put("paging", page);
		return result;
	}
	
	/**
	 * 删除货物
	 * @param staff
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteGoods.do")
	public JSONObject deleteStaff(Goods goods) {
		JSONObject result = new JSONObject();
		if (goods.getId() == null) return result;
		this.goodsService.delete(goods);
		logger.info("{}删除了货物{}", getUserRealName(), goods.getGoodsName());
		return result;
	}
	
	/**
	 * 出入库
	 * @param staff
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/changeAmount.do")
	public JSONObject changeAmount(String id, int amount, String flag) {
		JSONObject result = new JSONObject();
		if (id == null) return result;
		if (amount == 0) return result;
		if (flag == null) return result;
		Goods goods = new Goods();
		goods.setId(id);
		goods = this.goodsService.get(goods);
		if (flag.equals("1")) goods.setAmount(goods.getAmount() + amount);
		else goods.setAmount(goods.getAmount() - amount);
		GoodsFlow goodsFlow = new GoodsFlow();
		goodsFlow.setId(IDUtils.getUUID());
		goodsFlow.setAmount(amount);
		goodsFlow.setFlag(flag);
		goodsFlow.setGoodsId(goods.getId());
		goodsFlow.setCreateTime(new Date());
		this.goodsService.updateAndSaveGoods(goods, goodsFlow);
		if (flag.equals("1")) logger.info("{}入库了货物{}共{}", getUserRealName(), goods.getGoodsName(), amount);
		else logger.info("{}出库了货物{}共{}", getUserRealName(), goods.getGoodsName(), amount);
		return result;
	}
	
}