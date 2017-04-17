package com.gcgProject.service;

import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Goods;
import com.gcgProject.entity.GoodsFlow;

/**
 * 货物表
 * @author gcg
 * @date 2017-02-28 28:21:33
 */
public interface GoodsService {

	/**
	 * 保存货物表
	 * @param goods
	 * @return
	 */
	public Object save(Goods goods);
	
	/**
	 * 删除货物表（按主键）
	 * @param goods
	 * @return
	 */
	public int delete(Goods goods);

	/**
	 * 修改货物表（按主键）
	 * @param goods
	 * @return
	 */
	public int update(Goods goods);

	/**
	 * 获取货物表（按主键）
	 * @param goods
	 * @return
	 */
	public Goods get(Goods goods);
	
	/**
	 * 获取货物表列表
	 * @param map
	 * @return
	 */
	public List<Goods> find(Map<String, Object> map);
	
	/**
	 * 获取货物表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<Goods> page, Map<String, Object> map);

	/**
	 * 出入库
	 * @param id
	 * @param amount
	 * @param flag
	 */
	public void updateAndSaveGoods(Goods goods, GoodsFlow goodsFlow);
	
}