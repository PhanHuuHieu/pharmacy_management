package pharmacy.management.bean;

import org.springframework.beans.support.PagedListHolder;

public class ResponsePage {
	private PagedListHolder<?> pages;
	private int currentIndex;
	private int totalPageCount;

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
