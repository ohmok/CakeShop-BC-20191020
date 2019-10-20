package com.sikiedu.model;

import java.util.List;

public class Page {

	// 当前页数
	private int pageNo;
	// 每页显示数据
	private int pageSize;
	// 总数据
	private int totalCount;
	// 总页数
	private int tatolPage;

	private List<?> List;

	/**
	 * 控制每页显示数据
	 * 
	 * @param pageSize   页显示数据
	 * @param totalCount 总数据
	 */
	public void setPageSizeAndTotalCount(int pageSize, int totalCount) {

		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.tatolPage = (int) Math.ceil((double) totalCount / pageSize);
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTatolPage() {
		return tatolPage;
	}

	public void setTatolPage(int tatolPage) {
		this.tatolPage = tatolPage;
	}

	public List<?> getList() {
		return List;
	}

	public void setList(List<?> list) {
		List = list;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalCount=" + totalCount + ", tatolPage="
				+ tatolPage + ", List=" + List + "]";
	}

}
