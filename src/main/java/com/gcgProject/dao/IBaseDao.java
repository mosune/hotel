package com.gcgProject.dao;

import java.util.List;

public interface IBaseDao<T> {

	/**
	 * 根据条件获取一个元素
	 * @param paramMap
	 * @return
	 */
	T get(T entity);
	
	/**
	 * 插入记录
	 * @param entity
	 */
	int insert(T entity);

	/**
	 * 插入记录（批量）
	 * @param list
	 * @return
	 */
	/*int insert(List<T> list);*/
	
	/**
	 * 更新记录
	 * @param entity
	 * @return
	 */
	int update(T entity);

	/**
	 * 更新记录（批量）
	 * @param list
	 * @return
	 */
	int update(List<T> list);
	
	/**
	 * 删除记录
	 * @param obj
	 * @return
	 */
	int delete(T entity);
	
}
