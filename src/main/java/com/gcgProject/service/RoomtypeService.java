package com.gcgProject.service;

import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Roomtype;

/**
 * 房间类型表
 * @author gcg
 * @date 2017-02-25 25:12:39
 */
public interface RoomtypeService {

	/**
	 * 保存房间类型表
	 * @param roomtype
	 * @return
	 */
	public Object save(Roomtype roomtype);
	
	/**
	 * 删除房间类型表（按主键）
	 * @param roomtype
	 * @return
	 */
	public int delete(Roomtype roomtype);

	/**
	 * 修改房间类型表（按主键）
	 * @param roomtype
	 * @return
	 */
	public int update(Roomtype roomtype);

	/**
	 * 获取房间类型表（按主键）
	 * @param roomtype
	 * @return
	 */
	public Roomtype get(Roomtype roomtype);
	
	/**
	 * 获取房间类型表列表
	 * @param map
	 * @return
	 */
	public List<Roomtype> find(Map<String, Object> map);
	
	/**
	 * 获取房间类型表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<Roomtype> page, Map<String, Object> map);

	/**
	 * 通过id和状态查询房间类型
	 * @param id
	 * @param i
	 * @return
	 */
	public int queryCount(Integer id, int i);
	
}