package com.gcgProject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.GoodsFlow;
import com.gcgProject.entity.dto.GoodsFlowDto;
import com.gcgProject.dao.GoodsFlowDao;
import com.gcgProject.service.GoodsFlowService;


/**
 * 货物流水表
 * @author gcg
 * @date 2017-02-28 28:59:08
 */
@Service("goodsFlowService")
public class GoodsFlowServiceImpl implements GoodsFlowService {

	@Autowired
	private GoodsFlowDao goodsFlowDao;

	@Override
	public Object save(GoodsFlow goodsFlow) {
		return this.goodsFlowDao.insert(goodsFlow);
	}

	@Override
	public int delete(GoodsFlow goodsFlow) {
		return this.goodsFlowDao.delete(goodsFlow);
	}

	@Override
	public int update(GoodsFlow goodsFlow) {
		return this.goodsFlowDao.update(goodsFlow);
	}

	@Override
	public GoodsFlow get(GoodsFlow goodsFlow) {
		return this.goodsFlowDao.get(goodsFlow);
	}
	
	@Override
	public List<GoodsFlow> find(Map<String, Object> map) {
		return this.goodsFlowDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<GoodsFlowDto> page, Map<String, Object> map) {
		page.setList(this.goodsFlowDao.findPage(map));
		page.setCount(this.goodsFlowDao.findPageCount(map));
	}
	
}