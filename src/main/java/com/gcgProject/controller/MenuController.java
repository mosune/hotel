package com.gcgProject.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.gcgProject.entity.Menu;
import com.gcgProject.service.MenuService;
import com.gcgProject.util.PageResults;

/**
 * 菜单表
 * @author gcg
 * @date 2017-03-16 16:26:48
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/menu/index");
		return mv;
	}
	
	/**
	 * 菜单列表
	 * @param page
	 * @param searchData
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list.do")
	public JSONObject list(PageResults<Menu> page, String searchData) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", page.getStart());
		map.put("b", page.getEnd());
		map.put(Menu.FIELD_MENU_NAME, searchData);
		map.put(Menu.FIELD_PATH, searchData);
		this.menuService.findPage(page, map);
		result.put("paging", page);
		return result;
	}
	
}