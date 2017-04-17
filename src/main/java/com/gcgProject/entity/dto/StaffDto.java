package com.gcgProject.entity.dto;

import com.gcgProject.entity.Staff;

public class StaffDto extends Staff {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String posName; // 职位名

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

}
