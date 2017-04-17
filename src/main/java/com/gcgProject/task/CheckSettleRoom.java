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
 * 自动结账房间操作
 * @author Administrator
 *
 */
public class CheckSettleRoom {
	
	private Logger logger = LoggerFactory.getLogger(CheckSettleRoom.class);
	
	@Autowired
	private LiveService liveService;
	
	@Autowired
	private RoomService roomService;

	public void work() {  
        logger.info("自动结账房间操作任务开始");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Room.FIELD_FLAG, "1");
        map.put(Live.FIELD_DESTINE_TIME, "0");
        List<LiveDto> liveDtos = this.liveService.findRoomByFlag(map);
        Room room = new Room();
        Live live = new Live();
        for (LiveDto liveDto : liveDtos) {
			if (liveDto.getEndTime().before(new Date())) {
				room.setId(liveDto.getRoomId());
				room = this.roomService.get(room);
				room.setFlag("0");
				live.setId(liveDto.getId());
				live = this.liveService.get(live);
				live.setRealEndTime(new Date());
				this.roomService.update(room);
				this.liveService.update(live);
				logger.info("{}房间因过期而自动结账", liveDto.getRoomNum());
			}
		}
        logger.info("自动结账房间操作任务结束");
    } 
	
}
