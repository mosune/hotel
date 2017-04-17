package com.gcgProject.dao;

import java.util.List;
import java.util.Map;

import com.gcgProject.entity.Menu;

/**
 * 菜单表
 * @author gcg
 * @date 2017-03-16 16:26:47
 */
public interface MenuDao extends IBaseDao<Menu> {
	
	List<Menu> findPage(Map<String, Object> map);

	int findPageCount(Map<String, Object> map);

	List<Menu> find(Map<String, Object> map);

	List<Menu> findFristMenus();
	
}