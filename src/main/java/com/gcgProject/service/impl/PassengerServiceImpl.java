package com.gcgProject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Passenger;
import com.gcgProject.dao.PassengerDao;
import com.gcgProject.service.PassengerService;


/**
 * 旅客表
 * @author gcg
 * @date 2017-03-01 01:10:59
 */
@Service("passengerService")
public class PassengerServiceImpl implements PassengerService {

	@Autowired
	private PassengerDao passengerDao;

	@Override
	public Object save(Passenger passenger) {
		return this.passengerDao.insert(passenger);
	}

	@Override
	public int delete(Passenger passenger) {
		return this.passengerDao.delete(passenger);
	}

	@Override
	public int update(Passenger passenger) {
		return this.passengerDao.update(passenger);
	}

	@Override
	public Passenger get(Passenger passenger) {
		return this.passengerDao.get(passenger);
	}
	
	@Override
	public List<Passenger> find(Map<String, Object> map) {
		return this.passengerDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<Passenger> page, Map<String, Object> map) {
		page.setList(this.passengerDao.findPage(map));
		page.setCount(this.passengerDao.findPageCount(map));
	}
	
}