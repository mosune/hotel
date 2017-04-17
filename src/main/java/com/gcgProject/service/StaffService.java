package com.gcgProject.service;

import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;
import com.gcgProject.entity.Staff;
import com.gcgProject.entity.dto.StaffDto;

/**
 * 员工表
 * @author Lion.z
 * @date 2017-02-18 18:20:09
 */
public interface StaffService {

	/**
	 * 保存员工表
	 * @param staff
	 * @return
	 */
	public Object save(Staff staff);
	
	/**
	 * 删除员工表（按主键）
	 * @param staff
	 * @return
	 */
	public int delete(Staff staff);

	/**
	 * 修改员工表（按主键）
	 * @param staff
	 * @return
	 */
	public int update(Staff staff);

	/**
	 * 获取员工表（按主键）
	 * @param staff
	 * @return
	 */
	public Staff get(Staff staff);
	
	/**
	 * 获取员工表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<StaffDto> page, Map<String, Object> map);

	/**
	 * 查询员工
	 * @param map
	 * @return
	 */
	List<Staff> find(Map<String, Object> map);
	
}