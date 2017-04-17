package com.gcgProject.entity;

import java.util.List;

public class User {

	private String id; //员工id
	private String loginName; //登录名
	private String password; //密码
	private String realName; //真实姓名
	private Integer positionId; //职位id
	private List<Menu> menus; //职位列表
	private List<Menu> mainMenus; //主要职位列表
	public List<Menu> getMainMenus() {
		return mainMenus;
	}
	public void setMainMenus(List<Menu> mainMenus) {
		this.mainMenus = mainMenus;
	}
	public String getId() {
		return id;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	
}
