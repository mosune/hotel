package com.gcgProject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcgProject.util.PageResults;
import com.gcgProject.entity.Staff;
import com.gcgProject.entity.dto.StaffDto;
import com.gcgProject.dao.StaffDao;
import com.gcgProject.service.StaffService;


/**
 * 员工表
 * @author Lion.z
 * @date 2017-02-18 18:20:09
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao staffDao;

	public Object save(Staff staff) {
		return this.staffDao.insert(staff);
	}

	public int delete(Staff staff) {
		return this.staffDao.delete(staff);
	}

	public int update(Staff staff) {
		return this.staffDao.update(staff);
	}

	public Staff get(Staff staff) {
		return this.staffDao.get(staff);
	}
	
	@Override
	public List<Staff> find(Map<String, Object> map) {
		return this.staffDao.find(map);
	}
	
	@Override
	public void findPage(PageResults<StaffDto> page, Map<String, Object> map) {
		page.setList(this.staffDao.findPageDto(map));
		page.setCount(this.staffDao.findPageDtoCount(map));
	}
	
}