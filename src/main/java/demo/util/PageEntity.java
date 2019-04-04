package demo.util;

import java.util.List;

public class PageEntity<T> {

	private List<T> datas;
	private int totalCount;
	private int rowSize;
	private int currentPage;

	public PageEntity(List<T> datas, int totalCount, int rowSize, int currentPage) {
		this.datas = datas;
		this.totalCount = totalCount;
		this.rowSize = rowSize;
		this.currentPage = currentPage;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
