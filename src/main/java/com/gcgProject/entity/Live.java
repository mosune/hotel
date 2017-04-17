package com.gcgProject.entity;

import java.io.Serializable;

import java.lang.String;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 住客表
 * @author gcg
 * @date 2017-03-05 05:10:46
 */
public class Live implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_CUST_ID = "cust_id";
	public static final String FIELD_ROOM_ID = "room_id";
	public static final String FIELD_LIVE_TIME = "live_time";
	public static final String FIELD_END_TIME = "end_time";
	public static final String FIELD_REAL_END_TIME = "real_end_time";
	public static final String FIELD_DEPOSIT = "deposit";
	public static final String FIELD_CHARGE = "charge";
	public static final String FIELD_DESTINE_TIME = "destine_time";
	public static final String FIELD_CREATE_TIME = "create_time";
	
	private String id; //住房id
	private String custId; //旅客id
	private String roomId; //房间id
	private Date liveTime; //入住时间
	private Date endTime; //预计退房时间
	private Date realEndTime; //实际退房时间
	private BigDecimal deposit; //押金
	private BigDecimal charge; //房费
	private String destineTime; //是否预订
	private Date createTime; //创建时间
	
	public Live() {
		super();
	}

	public Live(String id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustId() {
		return this.custId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomId() {
		return this.roomId;
	}
	public void setLiveTime(Date liveTime) {
		this.liveTime = liveTime;
	}
	public Date getLiveTime() {
		return this.liveTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getEndTime() {
		return this.endTime;
	}
	public void setRealEndTime(Date realEndTime) {
		this.realEndTime = realEndTime;
	}
	public Date getRealEndTime() {
		return this.realEndTime;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public BigDecimal getDeposit() {
		return this.deposit;
	}
	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}
	public BigDecimal getCharge() {
		return this.charge;
	}
	public void setDestineTime(String destineTime) {
		this.destineTime = destineTime;
	}
	public String getDestineTime() {
		return this.destineTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	
}