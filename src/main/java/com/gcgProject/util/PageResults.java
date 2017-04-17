package com.gcgProject.util;

import java.io.Serializable;
import java.util.List;
/**
 * 分页封装，用于做分页查询的基础类，封装一些分页的相关属性
 * @author cheng
 *
 * @param <T>
 */
public class PageResults<T> implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int page; // 当前页
	private int sumPage; // 总页数
	private int count;// 总条数
	private int pageCount; // 每页条数
	private int start;	// 分页查询的起始位置
	private int end;	// 分页查询的末尾位置
	private List<T> list; // 分页出来的集合对象
	public void setPage(int page) {
		this.page = page;
	}
	public int getPage() {
		if(page == 0) page = 1;
		return page;
	}
	public int getSumPage() {
		sumPage = count % getPageCount() == 0 ? 
				count / getPageCount() : count / getPageCount() + 1;
		return sumPage;
	}
	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPageCount() {
		if (pageCount == 0) pageCount = 5;
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStart() {
		start = (getPage() - 1) * getPageCount();
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		end = getPage() * getPageCount();
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
 
}
