package com.gcgProject.entity;

import java.io.Serializable;

import java.lang.String;
import java.lang.Integer;

/**
 * 菜单表
 * @author gcg
 * @date 2017-03-16 16:26:46
 */
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_MENU_NAME = "menu_name";
	public static final String FIELD_PATH = "path";
	public static final String FIELD_MENU_ORDER = "menu_order";
	public static final String FIELD_PID = "pid";
	
	private Integer id; //id
	private String menuName; //名称
	private String path; //url地址
	private Integer menuOrder; //序号
	private Integer pid; //父级id
	
	public Menu() {
		super();
	}

	public Menu(Integer id) {
		this.id = id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuName() {
		return this.menuName;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPath() {
		return this.path;
	}
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	public Integer getMenuOrder() {
		return this.menuOrder;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getPid() {
		return this.pid;
	}
	
}