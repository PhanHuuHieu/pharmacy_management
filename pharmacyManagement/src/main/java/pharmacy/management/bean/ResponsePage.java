package pharmacy.management.bean;

import org.springframework.beans.support.PagedListHolder;

public class ResponsePage {
	private PagedListHolder<?> pages;
	private int currentIndex;
	private int totalPageCount;
	private int beginIndex;

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	private int endIndex;

	public PagedListHolder<?> getPages() {
		return pages;
	}

	public void setPages(PagedListHolder<?> pages) {
		this.pages = pages;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
}
