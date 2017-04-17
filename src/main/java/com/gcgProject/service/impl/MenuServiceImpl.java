package com.gcgProject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Menu;
import com.gcgProject.dao.MenuDao;
import com.gcgProject.service.MenuService;


/**
 * 菜单表
 * @author gcg
 * @date 2017-03-16 16:26:48
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public Object save(Menu menu) {
		return this.menuDao.insert(menu);
	}

	@Override
	public int delete(Menu menu) {
		return this.menuDao.delete(menu);
	}

	@Override
	public int update(Menu menu) {
		return this.menuDao.update(menu);
	}

	@Override
	public Menu get(Menu menu) {
		return this.menuDao.get(menu);
	}
	
	@Override
	public List<Menu> find(Map<String, Object> map) {
		return this.menuDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<Menu> page, Map<String, Object> map) {
		page.setList(this.menuDao.findPage(map));
		page.setCount(this.menuDao.findPageCount(map));
	}

	@Override
	public List<Menu> findFristMenus() {
		return this.menuDao.findFristMenus();
	}
	
}