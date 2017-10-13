package com.oa.personnel.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.personnel.entity.Post;

public interface IPostDao extends PagingAndSortingRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

}
