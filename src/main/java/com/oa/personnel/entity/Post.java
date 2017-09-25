package com.oa.personnel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_post")
public class Post {

	private Integer postId;
	private String postName;
	private String creatBy;			//创建人职工号
	private Department department;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getPostId() {
		return postId;
	}
	public String getPostName() {
		return postName;
	}
	public String getCreatBy() {
		return creatBy;
	}
	@ManyToOne
	public Department getDepartment() {
		return department;
	}
	
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public void setCreatBy(String creatBy) {
		this.creatBy = creatBy;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
