package com.ljk.programmer.page;

import org.springframework.stereotype.Component;

/***
 * 分页类封装
 * @author Administrator
 *
 */
@Component
public class Page {
	
	private int page;//当前页
	private int rows;//一页有多少数据
	private int offset;//偏移量
	public int getpage() {
		return page;
	}
	public void setpage(int page) {
		this.page = page;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getOffset() {
		
		this.offset =(page-1) *rows;
		return offset;
	}
	public void setOffset(int offset) {
		this.offset =(page-1) *rows;
	}
	
	

}
