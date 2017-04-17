package com.gcgProject.dao;

import java.util.List;
import java.util.Map;

import com.gcgProject.entity.Roomtype;

/**
 * 房间类型表
 * @author gcg
 * @date 2017-02-25 25:12:39
 */
public interface RoomtypeDao extends IBaseDao<Roomtype> {
	
	List<Roomtype> findPage(Map<String, Object> map);

	int findPageCount(Map<String, Object> map);

	List<Roomtype> find(Map<String, Object> map);

	int queryCount(Map<String, Object> map);
	
}