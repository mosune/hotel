package com.gcgProject.service;

import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;
import com.gcgProject.entity.Billflow;
import com.gcgProject.entity.Live;
import com.gcgProject.entity.Passenger;
import com.gcgProject.entity.Room;
import com.gcgProject.entity.dto.LiveDto;

/**
 * 住客表
 * @author gcg
 * @date 2017-03-01 01:29:45
 */
public interface LiveService {

	/**
	 * 保存住客表
	 * @param live
	 * @return
	 */
	public Object save(Live live);
	
	/**
	 * 删除住客表（按主键）
	 * @param live
	 * @return
	 */
	public int delete(Live live);

	/**
	 * 修改住客表（按主键）
	 * @param live
	 * @return
	 */
	public int update(Live live);

	/**
	 * 获取住客表（按主键）
	 * @param live
	 * @return
	 */
	public Live get(Live live);
	
	/**
	 * 获取住客表列表
	 * @param map
	 * @return
	 */
	public List<Live> find(Map<String, Object> map);
	
	/**
	 * 获取住客表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<Live> page, Map<String, Object> map);

	/**
	 * 入住
	 * @param live
	 * @param passenger
	 * @param billFlow
	 * @param room 
	 */
	public void saveAndUpdatePass(Live live, Passenger passenger, Billflow billFlow, Room room);

	/**
	 * 获取结账列表
	 * @param page
	 * @param map
	 */
	public void findPageSettle(PageResults<LiveDto> page, Map<String, Object> map);

	/**
	 * 结账
	 * @param live
	 * @param room
	 * @param billflow
	 */
	public void updateSettleRoom(Live live, Room room, Billflow billflow);

	/**
	 * 改变订单状态和房间状态
	 * @param live
	 * @param room
	 */
	public void updateLiveAndRoom(Live live, Room room);

	/**
	 * 续房
	 * @param live
	 * @param billflow
	 */
	public void updateContinueRoom(Live live, Billflow billflow);

	/**
	 * 获取首页列表
	 * @param map
	 * @return
	 */
	public List<LiveDto> findRoomByFlag(Map<String, Object> map);
	
}