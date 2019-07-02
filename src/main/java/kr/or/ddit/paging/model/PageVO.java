package kr.or.ddit.paging.model;

public class PageVO {
	
    private int page;	// 페이지 번호
	private int pageSize;	// 페이지 당 건수
	
	
	public PageVO() {

	}


	public PageVO(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}


	@Override
	public String toString() {
		return "PageVO [page=" + page + ", pageSize=" + pageSize + "]";
	}


	public int getPage() {
		return page == 0 ? 1 : page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getPageSize() {
		return pageSize == 0 ? 10 : pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
}
