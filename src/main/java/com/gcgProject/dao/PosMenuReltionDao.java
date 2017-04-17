package com.gcgProject.dao;

import java.util.List;
import java.util.Map;

import com.gcgProject.entity.PosMenuReltion;

/**
 * 角色职位关系表
 * @author gcg
 * @date 2017-03-16 16:26:45
 */
public interface PosMenuReltionDao extends IBaseDao<PosMenuReltion> {
	
	List<PosMenuReltion> findPage(Map<String, Object> map);

	int findPageCount(Map<String, Object> map);

	List<PosMenuReltion> find(Map<String, Object> map);

	void insertLists(List<PosMenuReltion> posMenuReltions);
	
}