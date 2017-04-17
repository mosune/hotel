package com.gcgProject.entity;

import java.io.Serializable;

import java.lang.String;
import java.lang.Integer;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 房间表
 * @author gcg
 * @date 2017-02-27 27:08:44
 */
public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_ROOM_NUM = "room_num";
	public static final String FIELD_TYPE_ID = "type_id";
	public static final String FIELD_PRICE = "price";
	public static final String FIELD_TV_FLAG = "tv_flag";
	public static final String FIELD_AIRCON_FLAG = "aircon_flag";
	public static final String FIELD_SHOWER_FLAG = "shower_flag";
	public static final String FIELD_WINDOW_FLAG = "window_flag";
	public static final String FIELD_REMARK = "remark";
	public static final String FIELD_FLAG = "flag";
	public static final String FIELD_CREATE_TIME = "create_time";
	
	private String id; //房间id
	private String roomNum; //房间编号
	private Integer typeId; //房间类型
	private BigDecimal price; //单天价格
	private String tvFlag; //有无电视
	private String airconFlag; //有无空调
	private String showerFlag; //有无浴室
	private String windowFlag; //有无窗户
	private String remark; //备注
	private String flag; //状态
	private Date createTime; //创建时间
	
	public Room() {
		super();
	}

	public Room(String id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getRoomNum() {
		return this.roomNum;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Integer getTypeId() {
		return this.typeId;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getPrice() {
		return this.price;
	}
	public void setTvFlag(String tvFlag) {
		this.tvFlag = tvFlag;
	}
	public String getTvFlag() {
		return this.tvFlag;
	}
	public void setAirconFlag(String airconFlag) {
		this.airconFlag = airconFlag;
	}
	public String getAirconFlag() {
		return this.airconFlag;
	}
	public void setShowerFlag(String showerFlag) {
		this.showerFlag = showerFlag;
	}
	public String getShowerFlag() {
		return this.showerFlag;
	}
	public void setWindowFlag(String windowFlag) {
		this.windowFlag = windowFlag;
	}
	public String getWindowFlag() {
		return this.windowFlag;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFlag() {
		return this.flag;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	
}