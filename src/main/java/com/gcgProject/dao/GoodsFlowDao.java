package com.gcgProject.dao;

import java.util.List;
import java.util.Map;

import com.gcgProject.entity.GoodsFlow;
import com.gcgProject.entity.dto.GoodsFlowDto;

/**
 * 货物流水表
 * @author gcg
 * @date 2017-02-28 28:59:08
 */
public interface GoodsFlowDao extends IBaseDao<GoodsFlow> {
	
	List<GoodsFlowDto> findPage(Map<String, Object> map);

	int findPageCount(Map<String, Object> map);

	List<GoodsFlow> find(Map<String, Object> map);
	
}