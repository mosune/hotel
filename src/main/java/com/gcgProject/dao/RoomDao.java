package com.gcgProject.dao;

import java.util.List;
import java.util.Map;

import com.gcgProject.entity.Room;
import com.gcgProject.entity.dto.RoomDto;

/**
 * 房间表
 * @author gcg
 * @date 2017-02-27 27:00:18
 */
public interface RoomDao extends IBaseDao<Room> {
	
	List<RoomDto> findPage(Map<String, Object> map);

	int findPageCount(Map<String, Object> map);

	List<Room> find(Map<String, Object> map);

	int countRoomNum(int flag);
	
}