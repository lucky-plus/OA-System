package com.oa.message.entity.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.oa.message.entity.Resources;
import com.oa.staff.entity.UserInfornation;

public class ResourcesDTO {
	private Integer resId;
	private String resName;
	private String resIdentify;		//资料辨识
	private Date resDate;
	
	private String userId;//维护关联关系
	private String userName;
	
	private Date beginDate;
	private Date endDate;
	
	public static void  dtoToEntity(ResourcesDTO dto,Resources entity) {
		BeanUtils.copyProperties(dto, entity);
		String userId = dto.getUserId();
		
		if(userId!=null && !"".equals(userId.trim())) {
			UserInfornation user = new UserInfornation();
			user.setUserId(userId);//关联关系维护
			
			entity.setUser(user);
		}
	}
	
	public static void  entityToDto(Resources entity , ResourcesDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		
		UserInfornation user = entity.getUser();
		if(user!=null) {
			dto.setUserId(user.getUserId());
			dto.setUserName(user.getUserName());

		}
		
	}
	
	
	public Integer getResId() {
		return resId;
	}
	public String getResName() {
		return resName;
	}
	public String getResIdentify() {
		return resIdentify;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getResDate() {
		return resDate;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public void setResIdentify(String resIdentify) {
		this.resIdentify = resIdentify;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	   @SuppressWarnings("unused")
		public static Specification<Resources> getWhereClause(ResourcesDTO resourcesDTO)
	    {
			return new Specification<Resources>() {
				public Predicate toPredicate(Root<Resources> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					 //1.声明Predicate集合
					 List<Predicate> predicate = new ArrayList<>();
					 //2.根据orderQueryDTO查询条件动态添加Predicate
					 if(resourcesDTO.getResName()!=null) {
						 predicate.add(cb.like(root.get("resName").as(String.class),"%"+ resourcesDTO.getResName()+"%"));
					 }
					 if(resourcesDTO.getBeginDate()!=null && !"".equals(resourcesDTO.getBeginDate().toString())) {
						 predicate.add(cb.greaterThanOrEqualTo(root.get("resDate").as(Date.class), resourcesDTO.getBeginDate()));
					 }
					 if(resourcesDTO.getEndDate()!=null && !"".equals(resourcesDTO.getEndDate().toString())){
						 predicate.add(cb.lessThanOrEqualTo(root.get("resDate").as(Date.class), resourcesDTO.getEndDate()));
					 }

					 //3.根据Predicate集合生成并返回and 连接的 where条件
		             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
				
				}
			};
	    }

}
