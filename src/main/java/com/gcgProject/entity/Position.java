package com.gcgProject.entity;

import java.io.Serializable;

import java.lang.String;
import java.lang.Integer;

/**
 * 职位表
 * @author gcg
 * @date 2017-03-16 16:26:46
 */
public class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	
	private Integer id; //id
	private String name; //职位名称
	
	public Position() {
		super();
	}

	public Position(Integer id) {
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
	
}