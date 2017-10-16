package com.oa.personnel.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.personnel.entity.Department;

public interface IDeptDao extends PagingAndSortingRepository<Department, Integer>, CrudRepository<Department, Integer>,JpaSpecificationExecutor<Department> {

	@Query("from Department d where d.deptName like %?1%")
	public Page<Department> findAll(String deptName, Pageable pageable);
	
	@Query("from Department d where d.parentNode.id = null")
	public List<Department> findParentNodes();

	@Query("from Department d where d.parentNode.id = ?1")
	public List<Department> findChildNodes(Integer parentId);

	
}
