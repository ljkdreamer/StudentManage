package com.ljk.programmer.entity;

import org.springframework.stereotype.Component;

@Component
public class Grade {
	
	private Long id;//用户id,主键自增
	private String name;//
	private String remark;//
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
