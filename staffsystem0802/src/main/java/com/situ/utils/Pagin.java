package com.situ.utils;

/**
 * 分页操作
 *
 */

public class Pagin {
	private final int pageNo; //当前页
	private final int pageSize;  //页码大小
	private Long  total;//总数
	private int  navshowepage=5;//导航页码数
	private int navstartpage;//导航开始页码
	private int navendpage;//导航结束页码
	private Integer page;//总页数
	
	public int getNavstartpage() {
		return this.navstartpage;
	}

	public void setNavstartpage(int navstartpage) {
		this.navstartpage = navstartpage;
	}

	public int getNavendpage() {
		return this.navendpage;
	}

	public void setNavendpage(int navendpage) {
		this.navendpage = navendpage;
	}

	public Long getTotal() {
		return total;
		
	}

	public void setTotal(long total) {
		this.total = total;
			//总页数
			int page=(int)(total/pageSize);
			if(this.total % pageSize>0) {
				page++;
			}
		this.page=page;
		
		int half=this.navshowepage/2;
		
		int start=pageNo-half;
		if(start<1) {
			start=1;
		}
		
		int end=start+navshowepage-1;
		if(end>page) {
			end=page;
			 start = end - navshowepage + 1;
			  if (start < 1) {
	                start = 1;
	            }
		}
		this.navendpage=end;
		this.navstartpage=start;
		
		
	}



	public Pagin(int pageNo, int pageSize) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}
	//获取页数
	public int getOffset() {
		return (this.pageNo - 1) * pageSize;
	}

	public int getLimit() {
		return pageSize;
	}
	public  int  getpage() {
		return this.page;
		
	}

	public int getNavshowepage() {
		return navshowepage;
	}

	public void setNavshowepage(int navshowepage) {
		this.navshowepage = navshowepage;
	}

}
