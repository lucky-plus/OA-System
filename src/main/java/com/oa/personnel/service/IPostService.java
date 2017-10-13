package com.oa.personnel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.oa.personnel.entity.Post;
import com.oa.personnel.entity.dto.PostDTO;

public interface IPostService {

	public void save(PostDTO post);
	public void delete(Integer id);
	public void delete(Integer[] ids);
	public List<PostDTO> findAll();
	public Page<PostDTO> findAll(Pageable pageable);
	public Page<PostDTO> findAll(Specification<Post> spec, Pageable pageable);
	
	public List<PostDTO> findPostsByDeptId(Integer deptId);
	
}
