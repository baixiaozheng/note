
package com.note.common;

import java.util.ArrayList;
import java.util.List;

public class Page {

	private int currentPage;// 当前页

	private int pageSize;// 每页条数

	private long pageCount;// 总页数

	private long totalCount;// 总记录数

	private List<?> result = new ArrayList<>();// 结果集

	public Page() {

		pageSize = 10;
	}

	public Page(int pageSize) {

		this.pageSize = pageSize;
	}

	public Page(int i, int j) {

		pageSize = 10;
		currentPage = i;
		pageSize = j;
	}

	public Page(int i, int j, long l) {

		pageSize = 10;
		currentPage = i;
		pageSize = j;
		totalCount = l;
	}

	public Page(List<?> list) {

		pageSize = 10;
		result = list;
	}

	public int getCurrentPage() {

		return currentPage;
	}

	public void setCurrentPage(int i) {

		currentPage = i;
	}

	public long getPageCount() {
		if(totalCount<=0 || pageSize<=0)
			return 0;
		return (totalCount+pageSize-1)/pageSize;
	}

	public void setPageCount(long l) {

		pageCount = l;
	}

	public int getPageSize() {

		return pageSize;
	}

	public void setPageSize(int i) {

		pageSize = i;
	}

	public List<?> getResult() {

		return result;
	}

	public void setResult(List<?> list) {

		result = list;
	}

	public long getTotalCount() {

		return totalCount;
	}

	public void setTotalCount(long l) {

		totalCount = l;
	}

	public List<?> getResult(Class<?> clas) {

		return result;
	}

	/**
	 * 计算总页数
	 * 
	 * @param l
	 * @param i
	 * @return
	 */
	public static long getPageCount(long l, int i) {

		if (l < 0L || i <= 0)
			return 0L;
		if (l % (long) i == 0L)
			return l / (long) i;
		else return l / (long) i + 1L;
	}

	/**
	 * 封装page分页对象
	 * 
	 * @param totalCount
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Page toPo(long totalCount, int pageNumber, int pageSize) {

		Page page = new Page();
		long count = page.getPageCount(totalCount, pageSize);// 计算总页数
		long currentPage = pageNumber > count ? count : pageNumber;// 计算当前页
		page.setTotalCount(totalCount);// 设置总记录数
		page.setCurrentPage(Long.valueOf(currentPage).intValue());// 设置当前页
		page.setPageSize(pageSize);// 设置取多少条记录 默认10条
		page.setPageCount(count);// 设置总页数
		return page;
	}

	/**
	 * 转换成Json输出
	 */
	public String toString() {

		return (new StringBuilder()).append("Page [currentPage=").append(currentPage).append(", pageCount=").append(pageCount).append(", pageSize=").append(pageSize).append(", result.size()=")
				.append(result.size()).append(", totalCount=").append(totalCount).append("]").toString();
	}
}