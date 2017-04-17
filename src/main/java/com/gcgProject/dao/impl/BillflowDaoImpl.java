package com.gcgProject.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcgProject.entity.Billflow;
import com.gcgProject.dao.BillflowDao;

/**
 * 账单流水表
 * @author gcg
 * @date 2017-03-07 07:56:56
 */
@Repository("billflowDao")
public class BillflowDaoImpl extends BaseDaoImpl<Billflow> implements BillflowDao {
	
	@Override
	public List<Billflow> findPage(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName(SQL_SELECT_PAGE), map);
	}

	@Override
	public int findPageCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT_PAGECOUNT), map);
	}

	@Override
	public BigDecimal getCountBillFlow(String gathering) {
		return this.getSqlSession().selectOne(this.getSqlName("getCountBillFlow"), gathering);
	}
	
}