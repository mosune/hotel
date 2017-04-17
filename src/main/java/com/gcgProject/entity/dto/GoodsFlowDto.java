package com.gcgProject.entity.dto;

import com.gcgProject.entity.GoodsFlow;

public class GoodsFlowDto extends GoodsFlow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String FIELD_GOODS_NAME = "goods_name";
	
	private String goodsName; // 货物名

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

}
