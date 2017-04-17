package com.gcgProject.service;

import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Room;
import com.gcgProject.entity.dto.RoomDto;

/**
 * 房间表
 * @author gcg
 * @date 2017-02-27 27:00:18
 */
public interface RoomService {

	/**
	 * 保存房间表
	 * @param room
	 * @return
	 */
	public Object save(Room room);
	
	/**
	 * 删除房间表（按主键）
	 * @param room
	 * @return
	 */
	public int delete(Room room);

	/**
	 * 修改房间表（按主键）
	 * @param room
	 * @return
	 */
	public int update(Room room);

	/**
	 * 获取房间表（按主键）
	 * @param room
	 * @return
	 */
	public Room get(Room room);
	
	/**
	 * 获取房间表列表
	 * @param map
	 * @return
	 */
	public List<Room> find(Map<String, Object> map);
	
	/**
	 * 获取房间表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<RoomDto> page, Map<String, Object> map);

	/**
	 * 按照房间状态统计数量
	 * @param flag
	 * @return
	 */
	public int countRoomNum(int flag);
	
}