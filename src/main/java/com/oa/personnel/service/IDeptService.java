package com.oa.personnel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oa.personnel.entity.Department;


public interface IDeptService {

	public void save(Department dept);
	public void delete(Integer id);
	public void delete(Integer[] ids);
	public List<Department> findAll();
	public Page<Department> findAll(Pageable pageable);
	public Page<Department> findAll(String deptName, Pageable pageable);
	
}
