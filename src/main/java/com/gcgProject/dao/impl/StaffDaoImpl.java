package com.gcgProject.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gcgProject.entity.Staff;
import com.gcgProject.entity.dto.StaffDto;
import com.gcgProject.dao.StaffDao;

/**
 * 员工表
 * @author gcg
 * @date 2017-02-18 18:20:09
 */
@Repository("staffDao")
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao  {

	@Override
	public List<StaffDto> findPageDto(Map<String, Object> map) {
		return this.getSqlSession().selectList(this.getSqlName(SQL_SELECT_PAGE), map);
	}

	@Override
	public int findPageDtoCount(Map<String, Object> map) {
		return this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT_PAGECOUNT), map);
	}
	
}