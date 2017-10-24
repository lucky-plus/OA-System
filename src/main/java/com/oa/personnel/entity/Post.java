package com.oa.personnel.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_post")
public class Post {

	private Integer postId;
	private String postName;
	private String postDescribe;			
	private Department department;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getPostId() {
		return postId;
	}
	public String getPostName() {
		return postName;
	}
	public String getPostDescribe() {
		return postDescribe;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="deptId")
	public Department getDepartment() {
		return department;
	}
	
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	public void setPostDescribe(String postDescribe) {
		this.postDescribe = postDescribe;
	}
	
}
