package com.gcgProject.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcgProject.entity.PosMenuReltion;
import com.gcgProject.dao.PosMenuReltionDao;

/**
 * 角色职位关系表
 * @author gcg
 * @date 2017-03-16 16:26:45
 */
@Repository("posMenuReltionDao")
public class PosMenuReltionDaoImpl extends BaseDaoImpl<PosMenuReltion> implements PosMenuReltionDao {
	
	@Override
	public List<PosMenuReltion> findPage(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName(SQL_SELECT_PAGE), map);
	}

	@Override
	public int findPageCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT_PAGECOUNT), map);
	}

	@Override
	public void insertLists(List<PosMenuReltion> posMenuReltions) {
		this.getSqlSession().insert(this.getSqlName("batchInsert"), posMenuReltions);
	}
	
}