package com.oa.personnel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.personnel.entity.Post;

public interface IPostDao extends PagingAndSortingRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

	@Query("from Post p where p.department.deptId = ?1")
	public List<Post> findPostsByDeptId(Integer deptId);

}
