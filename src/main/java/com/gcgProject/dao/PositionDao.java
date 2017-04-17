package com.gcgProject.dao;

import java.util.List;
import java.util.Map;

import com.gcgProject.entity.Menu;
import com.gcgProject.entity.Position;

/**
 * 职位表
 * @author gcg
 * @date 2017-03-16 16:26:47
 */
public interface PositionDao extends IBaseDao<Position> {
	
	List<Position> findPage(Map<String, Object> map);

	int findPageCount(Map<String, Object> map);

	List<Position> find(Map<String, Object> map);

	List<Position> findPosition();

	List<Menu> getPosById(Integer positionId);
	
}