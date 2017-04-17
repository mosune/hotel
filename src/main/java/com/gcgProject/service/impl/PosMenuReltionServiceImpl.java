package com.gcgProject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.PosMenuReltion;
import com.gcgProject.dao.PosMenuReltionDao;
import com.gcgProject.service.PosMenuReltionService;


/**
 * 角色职位关系表
 * @author gcg
 * @date 2017-03-16 16:26:46
 */
@Service("posMenuReltionService")
public class PosMenuReltionServiceImpl implements PosMenuReltionService {

	@Autowired
	private PosMenuReltionDao posMenuReltionDao;

	@Override
	public Object save(PosMenuReltion posMenuReltion) {
		return this.posMenuReltionDao.insert(posMenuReltion);
	}

	@Override
	public int delete(PosMenuReltion posMenuReltion) {
		return this.posMenuReltionDao.delete(posMenuReltion);
	}

	@Override
	public int update(PosMenuReltion posMenuReltion) {
		return this.posMenuReltionDao.update(posMenuReltion);
	}

	@Override
	public PosMenuReltion get(PosMenuReltion posMenuReltion) {
		return this.posMenuReltionDao.get(posMenuReltion);
	}
	
	@Override
	public List<PosMenuReltion> find(Map<String, Object> map) {
		return this.posMenuReltionDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<PosMenuReltion> page, Map<String, Object> map) {
		page.setList(this.posMenuReltionDao.findPage(map));
		page.setCount(this.posMenuReltionDao.findPageCount(map));
	}
	
}