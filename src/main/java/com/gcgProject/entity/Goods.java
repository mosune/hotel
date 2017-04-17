package com.gcgProject.entity;

import java.io.Serializable;

import java.lang.String;
import java.lang.Integer;

/**
 * 货物表
 * @author gcg
 * @date 2017-02-28 28:21:31
 */
public class Goods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_GOODS_NAME = "goods_name";
	public static final String FIELD_AMOUNT = "amount";
	public static final String FIELD_REMARK = "remark";
	
	private String id; //货物id
	private String goodsName; //货物名字
	private Integer amount; //数量
	private String remark; //备注
	
	public Goods() {
		super();
	}

	public Goods(String id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsName() {
		return this.goodsName;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getAmount() {
		return this.amount;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return this.remark;
	}
	
}