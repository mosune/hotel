package com.gcgProject.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gcgProject.entity.Live;
import com.gcgProject.entity.Room;
import com.gcgProject.entity.dto.LiveDto;
import com.gcgProject.service.LiveService;
import com.gcgProject.service.RoomService;

/**
 * 检查预订房过期定时任务
 * @author Administrator
 *
 */
public class CheckOrderRoom {
	
	private Logger logger = LoggerFactory.getLogger(CheckOrderRoom.class);
	
	@Autowired
	private LiveService liveService;
	
	@Autowired
	private RoomService roomService;

	public void work() {  
        logger.info("检查是否含有过期的预定任务开始");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Room.FIELD_FLAG, "1");
        map.put(Live.FIELD_DESTINE_TIME, "1");
        List<LiveDto> liveDtos = this.liveService.findRoomByFlag(map);
        Room room = new Room();
        for (LiveDto liveDto : liveDtos) {
			if (liveDto.getLiveTime().before(new Date())) {
				room.setId(liveDto.getRoomId());
				room = this.roomService.get(room);
				room.setFlag("0");
				this.roomService.update(room);
				logger.info("{}房间因过期而退出预定", liveDto.getRoomNum());
			}
		}
        logger.info("检查是否含有过期的预定任务结束");
    } 
	
}
