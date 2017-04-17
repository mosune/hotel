package com.gcgProject.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcgProject.entity.Passenger;
import com.gcgProject.dao.PassengerDao;

/**
 * 旅客表
 * @author gcg
 * @date 2017-03-01 01:10:59
 */
@Repository("passengerDao")
public class PassengerDaoImpl extends BaseDaoImpl<Passenger> implements PassengerDao {
	
	@Override
	public List<Passenger> findPage(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName(SQL_SELECT_PAGE), map);
	}

	@Override
	public int findPageCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT_PAGECOUNT), map);
	}
	
}