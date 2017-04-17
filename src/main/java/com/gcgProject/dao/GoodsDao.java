package com.gcgProject.dao;

import java.util.List;
import java.util.Map;

import com.gcgProject.entity.Goods;

/**
 * 货物表
 * @author gcg
 * @date 2017-02-28 28:21:32
 */
public interface GoodsDao extends IBaseDao<Goods> {
	
	List<Goods> findPage(Map<String, Object> map);

	int findPageCount(Map<String, Object> map);

	List<Goods> find(Map<String, Object> map);
	
}