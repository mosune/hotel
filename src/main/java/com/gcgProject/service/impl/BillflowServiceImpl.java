package com.gcgProject.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Billflow;
import com.gcgProject.dao.BillflowDao;
import com.gcgProject.service.BillflowService;


/**
 * 账单流水表
 * @author gcg
 * @date 2017-03-07 07:56:57
 */
@Service("billflowService")
public class BillflowServiceImpl implements BillflowService {

	@Autowired
	private BillflowDao billflowDao;

	@Override
	public Object save(Billflow billflow) {
		return this.billflowDao.insert(billflow);
	}

	@Override
	public int delete(Billflow billflow) {
		return this.billflowDao.delete(billflow);
	}

	@Override
	public int update(Billflow billflow) {
		return this.billflowDao.update(billflow);
	}

	@Override
	public Billflow get(Billflow billflow) {
		return this.billflowDao.get(billflow);
	}
	
	@Override
	public List<Billflow> find(Map<String, Object> map) {
		return this.billflowDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<Billflow> page, Map<String, Object> map) {
		page.setList(this.billflowDao.findPage(map));
		page.setCount(this.billflowDao.findPageCount(map));
	}

	@Override
	public BigDecimal getCountBillFlow(String gathering) {
		return this.billflowDao.getCountBillFlow(gathering);
	}
	
}