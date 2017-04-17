package com.gcgProject.dao;

import java.util.List;
import java.util.Map;

import com.gcgProject.entity.Live;
import com.gcgProject.entity.dto.LiveDto;

/**
 * 住客表
 * @author gcg
 * @date 2017-03-01 01:29:44
 */
public interface LiveDao extends IBaseDao<Live> {
	
	List<Live> findPage(Map<String, Object> map);

	int findPageCount(Map<String, Object> map);

	List<Live> find(Map<String, Object> map);

	List<LiveDto> findPageSettle(Map<String, Object> map);

	int findPageSettleCount(Map<String, Object> map);

	List<LiveDto> findRoomByFlag(Map<String, Object> map);
	
}