package com.oa.personnel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oa.personnel.entity.Department;
import com.oa.personnel.web.TreeNode;


public interface IDeptService {

	public void save(Department dept);
	public void delete(Integer id);
	public void delete(Integer[] ids);
	public void delete(Department dept);
	public List<Department> findAll();
	public Department findOne(Integer id);
	public Page<Department> findAll(Pageable pageable);
	public Page<Department> findAll(String deptName, Pageable pageable);
	public List<TreeNode> findNodes(Integer parentId);
	
}
