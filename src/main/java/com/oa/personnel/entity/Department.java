package com.oa.personnel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.oa.staff.entity.UserInfornation;

@Entity
@Table(name="t_dept")
public class Department {

	private Integer deptId;
	private String deptName;
	private String creatBy;			//创建人职工号
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getDeptId() {
		return deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public String getCreatBy() {
		return creatBy;
	}
	
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setCreatBy(String creatBy) {
		this.creatBy = creatBy;
	}
	
}
