package com.gcgProject.entity.dto;

import java.math.BigDecimal;

import com.gcgProject.entity.Live;

public class LiveDto extends Live {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String roomId; // 房间id
	
	private String roomNum; // 房间名
	
	private String passengerName; // 游客名
	
	private BigDecimal price; // 单天价格

	public BigDecimal getPrice() {
		return price;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

}
