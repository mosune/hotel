package com.gcgProject.service;

import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.GoodsFlow;
import com.gcgProject.entity.dto.GoodsFlowDto;

/**
 * 货物流水表
 * @author gcg
 * @date 2017-02-28 28:59:08
 */
public interface GoodsFlowService {

	/**
	 * 保存货物流水表
	 * @param goodsFlow
	 * @return
	 */
	public Object save(GoodsFlow goodsFlow);
	
	/**
	 * 删除货物流水表（按主键）
	 * @param goodsFlow
	 * @return
	 */
	public int delete(GoodsFlow goodsFlow);

	/**
	 * 修改货物流水表（按主键）
	 * @param goodsFlow
	 * @return
	 */
	public int update(GoodsFlow goodsFlow);

	/**
	 * 获取货物流水表（按主键）
	 * @param goodsFlow
	 * @return
	 */
	public GoodsFlow get(GoodsFlow goodsFlow);
	
	/**
	 * 获取货物流水表列表
	 * @param map
	 * @return
	 */
	public List<GoodsFlow> find(Map<String, Object> map);
	
	/**
	 * 获取货物流水表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<GoodsFlowDto> page, Map<String, Object> map);
	
}