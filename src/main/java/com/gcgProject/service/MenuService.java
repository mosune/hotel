package com.gcgProject.service;

import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Menu;

/**
 * 菜单表
 * @author gcg
 * @date 2017-03-16 16:26:48
 */
public interface MenuService {

	/**
	 * 保存菜单表
	 * @param menu
	 * @return
	 */
	public Object save(Menu menu);
	
	/**
	 * 删除菜单表（按主键）
	 * @param menu
	 * @return
	 */
	public int delete(Menu menu);

	/**
	 * 修改菜单表（按主键）
	 * @param menu
	 * @return
	 */
	public int update(Menu menu);

	/**
	 * 获取菜单表（按主键）
	 * @param menu
	 * @return
	 */
	public Menu get(Menu menu);
	
	/**
	 * 获取菜单表列表
	 * @param map
	 * @return
	 */
	public List<Menu> find(Map<String, Object> map);
	
	/**
	 * 获取菜单表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<Menu> page, Map<String, Object> map);

	/**
	 * 找到一级菜单
	 * @return
	 */
	public List<Menu> findFristMenus();
	
}