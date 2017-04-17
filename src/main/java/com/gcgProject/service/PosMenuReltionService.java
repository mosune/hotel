package com.gcgProject.service;

import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.PosMenuReltion;

/**
 * 角色职位关系表
 * @author gcg
 * @date 2017-03-16 16:26:46
 */
public interface PosMenuReltionService {

	/**
	 * 保存角色职位关系表
	 * @param posMenuReltion
	 * @return
	 */
	public Object save(PosMenuReltion posMenuReltion);
	
	/**
	 * 删除角色职位关系表（按主键）
	 * @param posMenuReltion
	 * @return
	 */
	public int delete(PosMenuReltion posMenuReltion);

	/**
	 * 修改角色职位关系表（按主键）
	 * @param posMenuReltion
	 * @return
	 */
	public int update(PosMenuReltion posMenuReltion);

	/**
	 * 获取角色职位关系表（按主键）
	 * @param posMenuReltion
	 * @return
	 */
	public PosMenuReltion get(PosMenuReltion posMenuReltion);
	
	/**
	 * 获取角色职位关系表列表
	 * @param map
	 * @return
	 */
	public List<PosMenuReltion> find(Map<String, Object> map);
	
	/**
	 * 获取角色职位关系表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<PosMenuReltion> page, Map<String, Object> map);
	
}