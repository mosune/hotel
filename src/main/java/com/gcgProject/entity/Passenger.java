package com.gcgProject.entity;

import java.io.Serializable;

import java.lang.String;
import java.lang.Integer;

/**
 * 旅客表
 * @author gcg
 * @date 2017-03-07 07:45:57
 */
public class Passenger implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_ID_CODE = "id_code";
	public static final String FIELD_VIP_ID = "vip_id";
	public static final String FIELD_VIP_INTEGRAL = "vip_integral";
	
	private String id; //旅客id
	private String name; //姓名
	private String idCode; //身份证号
	private String vipId; //会员id
	private Integer vipIntegral; //会员积分
	
	public Passenger() {
		super();
	}

	public Passenger(String id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	public String getIdCode() {
		return this.idCode;
	}
	public void setVipId(String vipId) {
		this.vipId = vipId;
	}
	public String getVipId() {
		return this.vipId;
	}
	public void setVipIntegral(Integer vipIntegral) {
		this.vipIntegral = vipIntegral;
	}
	public Integer getVipIntegral() {
		return this.vipIntegral;
	}
	
}