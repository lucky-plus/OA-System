package com.oa.personnel.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import com.oa.personnel.entity.Department;
import com.oa.personnel.entity.Post;

public class PostDTO {

	private Integer postId;
	private String postName;
	private String creatBy;			//创建人职工号
	private String postDescribe;
	
	private Integer deptId;
	private String deptName;
	
	public static void entityToDto(Post entity, PostDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		if(entity.getDepartment() != null) {
			dto.setDeptId(entity.getDepartment().getDeptId());
			dto.setDeptName(entity.getDepartment().getDeptName());
		}
	}
	
	public static void dtoToEntity(PostDTO dto, Post entity) {
		BeanUtils.copyProperties(dto, entity);
		if(dto.getDeptId() != null) {
			Department dept = new Department();
			dept.setDeptId(dto.getDeptId());
			entity.setDepartment(dept);
		}
	}
	
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getPostName() {
		return postName;
	}
	public String getPostDescribe() {
		return postDescribe;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getCreatBy() {
		return creatBy;
	}
	public void setCreatBy(String creatBy) {
		this.creatBy = creatBy;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public void setPostDescribe(String postDescribe) {
		this.postDescribe = postDescribe;
	}

	/**
     * 动态生成where语句
     */
	public static Specification<Post> getWhereClause(PostDTO postDTO) {
		return new Specification<Post>() {
			public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 //1.声明Predicate集合
				 List<Predicate> predicate = new ArrayList<>();
				 //2.根据orderQueryDTO查询条件动态添加Predicate
				 if(postDTO.getPostName()!=null && !"".equals(postDTO.getPostName())) {
					 predicate.add(cb.like(root.get("postName").as(String.class),"%"+ postDTO.getPostName()+"%"));
				 }
				 if(postDTO.getDeptName()!=null && !"".equals(postDTO.getDeptName())) {
					 predicate.add(cb.like(root.get("department").get("deptName").as(String.class),"%"+ postDTO.getDeptName()+"%"));
				 }
				 //3.根据Predicate集合生成并返回and 连接的 where条件
	             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			
			}
		};
	}
	
}
