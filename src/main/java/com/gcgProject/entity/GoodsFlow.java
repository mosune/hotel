package com.gcgProject.entity;

import java.io.Serializable;

import java.lang.String;
import java.lang.Integer;
import java.util.Date;

/**
 * 货物流水表
 * @author gcg
 * @date 2017-02-28 28:28:22
 */
public class GoodsFlow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_GOODS_ID = "goods_id";
	public static final String FIELD_FLAG = "flag";
	public static final String FIELD_AMOUNT = "amount";
	public static final String FIELD_CREATE_TIME = "create_time";
	
	private String id; //流水id
	private String goodsId; //货物id
	private String flag; //出入库flag
	private Integer amount; //数量
	private Date createTime; //创建时间
	
	public GoodsFlow() {
		super();
	}

	public GoodsFlow(String id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsId() {
		return this.goodsId;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFlag() {
		return this.flag;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getAmount() {
		return this.amount;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	
}