package com.oa.activiti.entity.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.oa.activiti.entity.formParam;
import com.oa.activiti.entity.formParamList;
import com.oa.staff.entity.UserInfornation;

public class formQueryDTO {
	private int formId;
	private String keyName;
	private List<formParam> lfp;
	private String createDate;
	private String userName;
	private String userId;
	private String description;
	
	public static void entityToDto(formParamList entity, formQueryDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		if(entity.getUserInfornation() != null) {
			dto.setUserName(entity.getUserInfornation().getUserName());
		}
	}	
	public static void dtoToEntity(formQueryDTO dto, formParamList entity) {
		BeanUtils.copyProperties(dto, entity);
		UserInfornation user = new UserInfornation();
		user.setUserId(dto.getUserId());
		entity.setUserInfornation(user);
		if(dto.getCreateDate()==null||"".equals(dto.getCreateDate())) {
			entity.setCreateDate(new Date());
		}
	}
	
	public int getFormId() {
		return formId;
	}
	public String getKeyName() {
		return keyName;
	}
	public List<formParam> getLfp() {
		return lfp;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public String getCreateDate() {
		return createDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setFormId(int formId) {
		this.formId = formId;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public void setLfp(List<formParam> lfp) {
		this.lfp = lfp;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static Specification<formParamList> getWhereClause(formQueryDTO dto) {
		return new Specification<formParamList>() {
			public Predicate toPredicate(Root<formParamList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 //1.声明Predicate集合
				 List<Predicate> predicate = new ArrayList<>();
				 //2.根据DTO查询条件动态添加Predicate
				 if(dto.getKeyName()!=null && !"".equals(dto.getKeyName())) {
					 predicate.add(cb.equal(root.get("createId").as(String.class),dto.getKeyName()));
				 }
				 if(dto.getUserName()!=null && !"".equals(dto.getUserName())) {
					 predicate.add(cb.like(root.get("user").get("userName").as(String.class),"%"+dto.getUserId()+"%"));
				 }
				 if(dto.getCreateDate()!=null && !"".equals(dto.getCreateDate())) {
					 try {
						predicate.add(cb.greaterThanOrEqualTo(root.get("createDate").as(Date.class), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dto.getCreateDate())));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				 //3.根据Predicate集合生成并返回and 连接的 where条件
	             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			
			}
		};
	}
}
