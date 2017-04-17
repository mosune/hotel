package com.gcgProject.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcgProject.entity.Roomtype;
import com.gcgProject.dao.RoomtypeDao;

/**
 * 房间类型表
 * @author gcg
 * @date 2017-02-25 25:12:39
 */
@Repository("roomtypeDao")
public class RoomtypeDaoImpl extends BaseDaoImpl<Roomtype> implements RoomtypeDao  {
	
	@Override
	public List<Roomtype> findPage(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName(SQL_SELECT_PAGE), map);
	}

	@Override
	public int findPageCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT_PAGECOUNT), map);
	}

	@Override
	public int queryCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName("queryCount"), map);
	}
	
}