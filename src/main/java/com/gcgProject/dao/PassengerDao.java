package com.gcgProject.dao;

import java.util.List;
import java.util.Map;

import com.gcgProject.entity.Passenger;

/**
 * 旅客表
 * @author gcg
 * @date 2017-03-01 01:10:59
 */
public interface PassengerDao extends IBaseDao<Passenger> {
	
	List<Passenger> findPage(Map<String, Object> map);

	int findPageCount(Map<String, Object> map);

	List<Passenger> find(Map<String, Object> map);
	
}