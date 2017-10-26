package com.oa.personnel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.oa.personnel.entity.Post;

public interface IPostDao extends PagingAndSortingRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

	@Query("from Post p where p.department.deptId = ?1")
	public List<Post> findPostsByDeptId(Integer deptId);

	@Modifying
	@Transactional
	@Query("update UserInfornation u set u.post.postId = ?2 where u.userId = ?1")
	public void updateUserPost(String userId, Integer postId);

	@Query("from Post p where postId = ?1")
	public Post findPostNameByPostId(Integer postId);
}
