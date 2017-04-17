package com.gcgProject.service;

import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;

import com.gcgProject.entity.Passenger;

/**
 * 旅客表
 * @author gcg
 * @date 2017-03-01 01:10:59
 */
public interface PassengerService {

	/**
	 * 保存旅客表
	 * @param passenger
	 * @return
	 */
	public Object save(Passenger passenger);
	
	/**
	 * 删除旅客表（按主键）
	 * @param passenger
	 * @return
	 */
	public int delete(Passenger passenger);

	/**
	 * 修改旅客表（按主键）
	 * @param passenger
	 * @return
	 */
	public int update(Passenger passenger);

	/**
	 * 获取旅客表（按主键）
	 * @param passenger
	 * @return
	 */
	public Passenger get(Passenger passenger);
	
	/**
	 * 获取旅客表列表
	 * @param map
	 * @return
	 */
	public List<Passenger> find(Map<String, Object> map);
	
	/**
	 * 获取旅客表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<Passenger> page, Map<String, Object> map);
	
}