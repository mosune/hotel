package com.gcgProject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Goods;
import com.gcgProject.entity.GoodsFlow;
import com.gcgProject.dao.GoodsDao;
import com.gcgProject.dao.GoodsFlowDao;
import com.gcgProject.service.GoodsService;


/**
 * 货物表
 * @author gcg
 * @date 2017-02-28 28:21:33
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private GoodsFlowDao goodsFlewDao;

	@Override
	public Object save(Goods goods) {
		return this.goodsDao.insert(goods);
	}

	@Override
	public int delete(Goods goods) {
		return this.goodsDao.delete(goods);
	}

	@Override
	public int update(Goods goods) {
		return this.goodsDao.update(goods);
	}

	@Override
	public Goods get(Goods goods) {
		return this.goodsDao.get(goods);
	}
	
	@Override
	public List<Goods> find(Map<String, Object> map) {
		return this.goodsDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<Goods> page, Map<String, Object> map) {
		page.setList(this.goodsDao.findPage(map));
		page.setCount(this.goodsDao.findPageCount(map));
	}

	@Override
	public void updateAndSaveGoods(Goods goods, GoodsFlow goodsFlow) {
		this.goodsFlewDao.insert(goodsFlow);
		this.goodsDao.update(goods);
	}
	
}