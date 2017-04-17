package com.gcgProject.service;

import java.util.List;
import java.util.Map;

import com.gcgProject.util.PageResults;
import com.gcgProject.entity.Menu;
import com.gcgProject.entity.PosMenuReltion;
import com.gcgProject.entity.Position;

/**
 * 职位表
 * @author gcg
 * @date 2017-03-16 16:26:48
 */
public interface PositionService {

	/**
	 * 保存职位表
	 * @param position
	 * @return
	 */
	public Object save(Position position);
	
	/**
	 * 删除职位表（按主键）
	 * @param position
	 * @return
	 */
	public int delete(Position position);

	/**
	 * 修改职位表（按主键）
	 * @param position
	 * @return
	 */
	public int update(Position position);

	/**
	 * 获取职位表（按主键）
	 * @param position
	 * @return
	 */
	public Position get(Position position);
	
	/**
	 * 获取职位表列表
	 * @param map
	 * @return
	 */
	public List<Position> find(Map<String, Object> map);
	
	/**
	 * 获取职位表分页列表
	 * @param map
	 * @param pageParam
	 * @return
	 */
	public void findPage(PageResults<Position> page, Map<String, Object> map);

	/**
	 * 删除职位和其关系
	 * @param position
	 * @param posMenuReltion
	 */
	public void deletePosAndRelation(Position position, PosMenuReltion posMenuReltion);

	/**
	 * 修改权限
	 * @param posMenuReltions
	 * @param lists
	 */
	public void changePower(List<PosMenuReltion> posMenuReltions, List<PosMenuReltion> lists);

	/**
	 * 查找不是管理员的职位
	 * @return
	 */
	public List<Position> findPosition();

	/**
	 * 获取菜单信息
	 * @param positionId
	 * @return
	 */
	public List<Menu> getPosById(Integer positionId);
	
}