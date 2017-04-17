package com.gcgProject.dao;

import java.util.List;
import java.util.Map;

import com.gcgProject.entity.Staff;
import com.gcgProject.entity.dto.StaffDto;

/**
 * 员工表
 * @author gcg
 * @date 2017-02-18 18:20:09
 */
public interface StaffDao extends IBaseDao<Staff> {

	List<StaffDto> findPageDto(Map<String, Object> map);

	int findPageDtoCount(Map<String, Object> map);

	List<Staff> find(Map<String, Object> map);

}