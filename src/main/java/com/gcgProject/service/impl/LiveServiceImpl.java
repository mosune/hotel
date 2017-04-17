package com.gcgProject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;
import com.gcgProject.entity.Billflow;
import com.gcgProject.entity.Live;
import com.gcgProject.entity.Passenger;
import com.gcgProject.entity.Room;
import com.gcgProject.entity.dto.LiveDto;
import com.gcgProject.dao.BillflowDao;
import com.gcgProject.dao.LiveDao;
import com.gcgProject.dao.PassengerDao;
import com.gcgProject.dao.RoomDao;
import com.gcgProject.service.LiveService;


/**
 * 住客表
 * @author gcg
 * @date 2017-03-01 01:29:45
 */
@Service("liveService")
public class LiveServiceImpl implements LiveService {

	@Autowired
	private LiveDao liveDao;
	
	@Autowired
	private PassengerDao passengerDao;
	
	@Autowired
	private BillflowDao billflowDao;
	
	@Autowired
	private RoomDao roomDao;

	@Override
	public Object save(Live live) {
		return this.liveDao.insert(live);
	}

	@Override
	public int delete(Live live) {
		return this.liveDao.delete(live);
	}

	@Override
	public int update(Live live) {
		return this.liveDao.update(live);
	}

	@Override
	public Live get(Live live) {
		return this.liveDao.get(live);
	}
	
	@Override
	public List<Live> find(Map<String, Object> map) {
		return this.liveDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<Live> page, Map<String, Object> map) {
		page.setList(this.liveDao.findPage(map));
		page.setCount(this.liveDao.findPageCount(map));
	}

	@Override
	public void saveAndUpdatePass(Live live, Passenger passenger, Billflow billFlow, Room room) {
		this.liveDao.insert(live);
		if (passenger != null) this.passengerDao.insert(passenger);
		if (billFlow != null) this.billflowDao.insert(billFlow);
		this.roomDao.update(room);
	}

	@Override
	public void findPageSettle(PageResults<LiveDto> page, Map<String, Object> map) {
		page.setList(this.liveDao.findPageSettle(map));
		page.setCount(this.liveDao.findPageSettleCount(map));
	}

	@Override
	public void updateSettleRoom(Live live, Room room, Billflow billflow) {
		this.liveDao.update(live);
		this.roomDao.update(room);
		this.billflowDao.insert(billflow);
	}

	@Override
	public void updateLiveAndRoom(Live live, Room room) {
		this.liveDao.update(live);
		this.roomDao.update(room);
	}

	@Override
	public void updateContinueRoom(Live live, Billflow billflow) {
		this.liveDao.update(live);
		this.billflowDao.insert(billflow);
	}

	@Override
	public List<LiveDto> findRoomByFlag(Map<String, Object> map) {
		return this.liveDao.findRoomByFlag(map);
	}
	
}