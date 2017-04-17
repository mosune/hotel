package com.gcgProject.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcgProject.entity.Live;
import com.gcgProject.entity.dto.LiveDto;
import com.gcgProject.dao.LiveDao;

/**
 * 住客表
 * @author gcg
 * @date 2017-03-01 01:29:44
 */
@Repository("liveDao")
public class LiveDaoImpl extends BaseDaoImpl<Live> implements LiveDao {
	
	@Override
	public List<Live> findPage(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName(SQL_SELECT_PAGE), map);
	}

	@Override
	public int findPageCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT_PAGECOUNT), map);
	}

	@Override
	public List<LiveDto> findPageSettle(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName("findPageSettle"), map);
	}

	@Override
	public int findPageSettleCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName("findPageSettleCount"), map);
	}

	@Override
	public List<LiveDto> findRoomByFlag(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName("findRoomByFlag"), map);
	}
	
}