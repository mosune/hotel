package com.gcgProject.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Roomtype;
import com.gcgProject.dao.RoomtypeDao;
import com.gcgProject.service.RoomtypeService;


/**
 * 房间类型表
 * @author gcg
 * @date 2017-02-25 25:12:39
 */
@Service("roomtypeService")
public class RoomtypeServiceImpl implements RoomtypeService {

	@Autowired
	private RoomtypeDao roomtypeDao;

	@Override
	public Object save(Roomtype roomtype) {
		return this.roomtypeDao.insert(roomtype);
	}

	@Override
	public int delete(Roomtype roomtype) {
		return this.roomtypeDao.delete(roomtype);
	}

	@Override
	public int update(Roomtype roomtype) {
		return this.roomtypeDao.update(roomtype);
	}

	@Override
	public Roomtype get(Roomtype roomtype) {
		return this.roomtypeDao.get(roomtype);
	}
	
	@Override
	public List<Roomtype> find(Map<String, Object> map) {
		return this.roomtypeDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<Roomtype> page, Map<String, Object> map) {
		page.setList(this.roomtypeDao.findPage(map));
		page.setCount(this.roomtypeDao.findPageCount(map));
	}

	@Override
	public int queryCount(Integer id, int i) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("i", i);
		return this.roomtypeDao.queryCount(map);
	}
	
}