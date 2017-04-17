package com.gcgProject.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Billflow;

/**
 * 账单流水表
 * @author gcg
 * @date 2017-03-07 07:56:57
 */
public interface BillflowService {

	/**
	 * 保存账单流水表
	 * @param billflow
	 * @return
	 */
	public Object save(Billflow billflow);
	
	/**
	 * 删除账单流水表（按主键）
	 * @param billflow
	 * @return
	 */
	public int delete(Billflow billflow);

	/**
	 * 修改账单流水表（按主键）
	 * @param billflow
	 * @return
	 */
	public int update(Billflow billflow);

	/**
	 * 获取账单流水表（按主键）
	 * @param billflow
	 * @return
	 */
	public Billflow get(Billflow billflow);
	
	/**
	 * 获取账单流水表列表
	 * @param map
	 * @return
	 */
	public List<Billflow> find(Map<String, Object> map);
	
	/**
	 * 获取账单流水表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<Billflow> page, Map<String, Object> map);

	/**
	 * 获取总金额
	 * @param gathering
	 * @return
	 */
	public BigDecimal getCountBillFlow(String gathering);
	
}