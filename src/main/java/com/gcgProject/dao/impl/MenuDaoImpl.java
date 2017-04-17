package com.gcgProject.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcgProject.entity.Menu;
import com.gcgProject.dao.MenuDao;

/**
 * 菜单表
 * @author gcg
 * @date 2017-03-16 16:26:47
 */
@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao {
	
	@Override
	public List<Menu> findPage(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName(SQL_SELECT_PAGE), map);
	}

	@Override
	public int findPageCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT_PAGECOUNT), map);
	}

	@Override
	public List<Menu> findFristMenus() {
		return this.getSqlSession().selectList(this.getSqlName("findFristMenus"));
	}
	
}