package com.gcgProject.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcgProject.entity.GoodsFlow;
import com.gcgProject.entity.dto.GoodsFlowDto;
import com.gcgProject.dao.GoodsFlowDao;

/**
 * 货物流水表
 * @author gcg
 * @date 2017-02-28 28:59:08
 */
@Repository("goodsFlowDao")
public class GoodsFlowDaoImpl extends BaseDaoImpl<GoodsFlow> implements GoodsFlowDao {
	
	@Override
	public List<GoodsFlowDto> findPage(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName(SQL_SELECT_PAGE), map);
	}

	@Override
	public int findPageCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT_PAGECOUNT), map);
	}
	
}