package com.gcgProject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;
import com.gcgProject.entity.Menu;
import com.gcgProject.entity.PosMenuReltion;
import com.gcgProject.entity.Position;
import com.gcgProject.dao.PosMenuReltionDao;
import com.gcgProject.dao.PositionDao;
import com.gcgProject.service.PositionService;


/**
 * 职位表
 * @author gcg
 * @date 2017-03-16 16:26:48
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionDao positionDao;
	
	@Autowired
	private PosMenuReltionDao posMenuReltionDao;

	@Override
	public Object save(Position position) {
		return this.positionDao.insert(position);
	}

	@Override
	public int delete(Position position) {
		return this.positionDao.delete(position);
	}

	@Override
	public int update(Position position) {
		return this.positionDao.update(position);
	}

	@Override
	public Position get(Position position) {
		return this.positionDao.get(position);
	}
	
	@Override
	public List<Position> find(Map<String, Object> map) {
		return this.positionDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<Position> page, Map<String, Object> map) {
		page.setList(this.positionDao.findPage(map));
		page.setCount(this.positionDao.findPageCount(map));
	}

	@Override
	public void deletePosAndRelation(Position position, PosMenuReltion posMenuReltion) {
		this.positionDao.delete(position);
		if(posMenuReltion != null) this.posMenuReltionDao.delete(posMenuReltion);
	}

	@Override
	public void changePower(List<PosMenuReltion> posMenuReltions, List<PosMenuReltion> lists) {
		if (lists.size() > 0) this.posMenuReltionDao.delete(lists.get(0));
		this.posMenuReltionDao.insertLists(posMenuReltions);
	}

	@Override
	public List<Position> findPosition() {
		return this.positionDao.findPosition();
	}

	@Override
	public List<Menu> getPosById(Integer positionId) {
		return this.positionDao.getPosById(positionId);
	}
	
}