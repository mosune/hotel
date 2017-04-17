package com.gcgProject.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.gcgProject.dao.IBaseDao;

public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements IBaseDao<T> {

	public static final String SQL_SELECT = "select";
	public static final String SQL_SELECT_BY = "selectBy";
	public static final String SQL_SELECT_PAGE = "findPage";
	public static final String SQL_SELECT_PAGECOUNT = "findPageCount";
	public static final String SQL_INSERT = "insert";
	public static final String SQL_DELETE = "delete";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_BATCH_INSERT = "batchInsert";
	
	@Resource  
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {  
        super.setSqlSessionFactory(sqlSessionFactory);  
    }  
	
	@Autowired
	protected SqlSessionTemplate sessionTemplate;
	@Autowired
	protected SqlSessionFactory sessionFactory;
//	@Autowired
//	private DruidDataSource dataSource;

	@SuppressWarnings("unchecked")
	public T get(T entity) {
		if (entity == null) {
			return null;
		}
		Object result = this.getSqlSession().selectOne(this.getSqlName(SQL_SELECT), entity);
		if (result == null) {
			return null;
		}
		return (T) result;
	}
	
	public int insert(T entity) {
		if (entity == null) {
			throw new RuntimeException("T is null");
		}
		int result = this.sessionTemplate.insert(this.getSqlName(SQL_INSERT), entity);
		return result;
	}

	public int insert(List<T> list) {
		if (list == null || list.size() <= 0) {
			return 0;
		}
		this.sessionTemplate.insert(this.getSqlName(SQL_BATCH_INSERT), list);
		return list.size();
	}

	public int update(T entity) {
		if (entity == null) {
			throw new RuntimeException("");
		}
		int result = this.sessionTemplate.update(this.getSqlName(SQL_UPDATE), entity);
		return result;
	}

	public int update(List<T> list) {
		if (list == null || list.size() <= 0) {
			return 0;
		}
		int row, result = 0;
		for (T t : list) {
			row = this.update(t);
			result += row;
		}
		return result;
	}

	public int delete(T entity) {
		if (entity == null) {
			throw new RuntimeException("");
		}
		
		int result = this.sessionTemplate.delete(this.getSqlName(SQL_DELETE), entity);
		
		return result;
	}
	
	public List<T> find(Map<String, Object> paramMap) {
		paramMap = paramMap != null ? paramMap : (new HashMap<String, Object>());
		return this.find(paramMap, this.getSqlName(SQL_SELECT_BY));
	}

	public List<T> find(Map<String, Object> paramMap, String sqlId) {
		paramMap = paramMap != null ? paramMap : (new HashMap<String, Object>());
		
		List<T> list = this.sessionTemplate.selectList(sqlId, paramMap);;
		return list;
	}
	
	//~protected
	protected String getSqlName(String sqlId) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName());
		sb.append(".");
		sb.append(sqlId);
		
		return sb.toString();
	}
	
}
