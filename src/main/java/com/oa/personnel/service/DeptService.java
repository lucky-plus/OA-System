package com.oa.personnel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oa.personnel.dao.IDeptDao;
import com.oa.personnel.entity.Department;

@Service
public class DeptService implements IDeptService {

	@Autowired
	private IDeptDao deptDao;
	
	@Override
	public void save(Department dept) {
		deptDao.save(dept);
	}

	@Override
	public void delete(Integer id) {
		deptDao.delete(id);
	}

	@Override
	public void delete(Integer[] ids) {
		for(Integer id : ids) {
			deptDao.delete(id);
		}
	}

	@Override
	public List<Department> findAll() {
		return (List<Department>) deptDao.findAll();
	}

	@Override
	public Page<Department> findAll(Pageable pageable) {
		return deptDao.findAll(pageable);
	}

	@Override
	public Page<Department> findAll(String deptName, Pageable pageable) {
		return deptDao.findAll(deptName, pageable);
	}

}
