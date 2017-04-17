package com.gcgProject.entity.dto;

import com.gcgProject.entity.Room;

/**
 * 房间dto
 * @author gcg
 *
 */
public class RoomDto extends Room {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 房间名
	private String roomtypeName;
	
	public String getRoomtypeName() {
		return roomtypeName;
	}

	public void setRoomtypeName(String roomtypeName) {
		this.roomtypeName = roomtypeName;
	}
	
}
