package com.gcgProject.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcgProject.entity.Room;
import com.gcgProject.entity.dto.RoomDto;
import com.gcgProject.dao.RoomDao;

/**
 * 房间表
 * @author gcg
 * @date 2017-02-27 27:00:18
 */
@Repository("roomDao")
public class RoomDaoImpl extends BaseDaoImpl<Room> implements RoomDao {
	
	@Override
	public List<RoomDto> findPage(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName(SQL_SELECT_PAGE), map);
	}

	@Override
	public int findPageCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT_PAGECOUNT), map);
	}

	@Override
	public int countRoomNum(int flag) {
		return this.getSqlSession().selectOne(this.getSqlName("countRoomNum"), flag);
	}
	
}