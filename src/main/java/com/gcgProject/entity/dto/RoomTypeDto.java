package com.gcgProject.entity.dto;

import com.gcgProject.entity.Roomtype;

public class RoomTypeDto extends Roomtype {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 房间总数
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
