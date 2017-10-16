package com.oa.personnel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.oa.staff.entity.UserInfornation;

@Entity
@Table(name="t_dept")
public class Department {

	private Integer deptId;
	private String deptName;
	private String creatBy;			//创建人职工号
	private Department parentNode;//节点
	
	private List<Department> childNodes = new ArrayList<Department>(); //一个节点对多个子节点
	@ManyToOne(cascade=CascadeType.ALL)
	public Department getParentNode() {
		return parentNode;
	}

	@OneToMany(mappedBy="parentNode" ,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<Department> getChildNodes() {
		return childNodes;
	}
	
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

	public void setParentNode(Department parentNode) {
		this.parentNode = parentNode;
	}

	public void setChildNodes(List<Department> childNodes) {
		this.childNodes = childNodes;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + "]";
	}
	
}
