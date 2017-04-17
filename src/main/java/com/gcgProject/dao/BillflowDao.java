package com.gcgProject.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.gcgProject.entity.Billflow;

/**
 * 账单流水表
 * @author gcg
 * @date 2017-03-07 07:56:56
 */
public interface BillflowDao extends IBaseDao<Billflow> {
	
	List<Billflow> findPage(Map<String, Object> map);

	int findPageCount(Map<String, Object> map);

	List<Billflow> find(Map<String, Object> map);

	BigDecimal getCountBillFlow(String gathering);
	
}