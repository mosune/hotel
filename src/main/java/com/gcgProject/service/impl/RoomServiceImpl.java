package com.gcgProject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Room;
import com.gcgProject.entity.dto.RoomDto;
import com.gcgProject.dao.RoomDao;
import com.gcgProject.service.RoomService;


/**
 * 房间表
 * @author gcg
 * @date 2017-02-27 27:00:18
 */
@Service("roomService")
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;

	@Override
	public Object save(Room room) {
		return this.roomDao.insert(room);
	}

	@Override
	public int delete(Room room) {
		return this.roomDao.delete(room);
	}

	@Override
	public int update(Room room) {
		return this.roomDao.update(room);
	}

	@Override
	public Room get(Room room) {
		return this.roomDao.get(room);
	}
	
	@Override
	public List<Room> find(Map<String, Object> map) {
		return this.roomDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<RoomDto> page, Map<String, Object> map) {
		page.setList(this.roomDao.findPage(map));
		page.setCount(this.roomDao.findPageCount(map));
	}

	@Override
	public int countRoomNum(int flag) {
		return this.roomDao.countRoomNum(flag);
	}
	
}