package com.gcgProject.entity;

import java.io.Serializable;

import java.lang.String;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账单流水表
 * @author gcg
 * @date 2017-03-07 07:56:56
 */
public class Billflow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String GATHERING = "1"; // 收款
	public final static String REFUND = "2"; // 退款
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_TYPE = "type";
	public static final String FIELD_CHARGE = "charge";
	public static final String FIELD_CREATE_TIME = "create_time";
	
	private String id; //流水号id
	private String type; //流水类型
	private BigDecimal charge; //资金
	private Date createTime; //创建时间
	
	public Billflow() {
		super();
	}

	public Billflow(String id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return this.type;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public BigDecimal getCharge() {
		return this.charge;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	
}