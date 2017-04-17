package com.gcgProject.entity;

import java.io.Serializable;

import java.lang.String;
import java.lang.Integer;

/**
 * 角色职位关系表
 * @author gcg
 * @date 2017-03-16 16:26:45
 */
public class PosMenuReltion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_POS_ID = "pos_id";
	public static final String FIELD_MENU_ID = "menu_id";
	
	private Integer posId; //职位id
	private Integer menuId; //菜单id

	public PosMenuReltion() {
	}
	
	public void setPosId(Integer posId) {
		this.posId = posId;
	}
	public Integer getPosId() {
		return this.posId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getMenuId() {
		return this.menuId;
	}
	
}