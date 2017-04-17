package com.gcgProject.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcgProject.entity.Menu;
import com.gcgProject.entity.Position;
import com.gcgProject.dao.PositionDao;

/**
 * 职位表
 * @author gcg
 * @date 2017-03-16 16:26:47
 */
@Repository("positionDao")
public class PositionDaoImpl extends BaseDaoImpl<Position> implements PositionDao {
	
	@Override
	public List<Position> findPage(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName(SQL_SELECT_PAGE), map);
	}

	@Override
	public int findPageCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT_PAGECOUNT), map);
	}

	@Override
	public List<Position> findPosition() {
		return this.getSqlSession().selectList(this.getSqlName("findPosition"));
	}

	@Override
	public List<Menu> getPosById(Integer positionId) {
		return this.getSqlSession().selectList(this.getSqlName("getPosById"), positionId);
	}
	
}