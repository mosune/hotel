package com.gcgProject.entity;

import java.io.Serializable;

import java.lang.String;
import java.lang.Integer;

/**
 * 房间类型表
 * @author gcg
 * @date 2017-02-25 25:12:38
 */
public class Roomtype implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_REMARK = "remark";
	
	private Integer id; //
	private String name; //名字
	private String remark; //备注
	
	public Roomtype() {
		super();
	}

	public Roomtype(Integer id) {
		this.id = id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return this.remark;
	}
	
}