package com.oa.log.entity.dto;

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
import com.oa.log.entity.Log;

public class LogDTO {

	private Integer logId;
	private Date createDate;
	private String operation;
	private String content;
	private String userName;

	private Date beginDate;
	private Date endDate;
	
	public static void entityToDto(Log entity, LogDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		dto.setUserName(entity.getUser().getUserName());
	}
	
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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

	/**
     * 动态生成where语句
     */
	public static Specification<Log> getWhereClause(LogDTO logDTO) {
		return new Specification<Log>() {
			public Predicate toPredicate(Root<Log> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 //1.声明Predicate集合
				 List<Predicate> predicate = new ArrayList<>();
				 //2.根据orderQueryDTO查询条件动态添加Predicate
				 if(logDTO.getUserName()!=null && !"".equals(logDTO.getUserName())) {
					 predicate.add(cb.like(root.get("user").get("userName").as(String.class),"%"+ logDTO.getUserName()+"%"));
				 }
				 if(logDTO.getOperation()!=null && !"".equals(logDTO.getOperation())) {
					 predicate.add(cb.equal(root.get("operation").as(String.class), logDTO.getOperation()));
				 }
				 if(logDTO.getBeginDate()!=null && !"".equals(logDTO.getBeginDate().toString())) {
					 predicate.add(cb.greaterThanOrEqualTo(root.get("createDate").as(Date.class), logDTO.getBeginDate()));
				 }
				 if(logDTO.getEndDate()!=null && !"".equals(logDTO.getEndDate().toString())){
					 predicate.add(cb.lessThanOrEqualTo(root.get("createDate").as(Date.class), logDTO.getEndDate()));
				 }
				 //3.根据Predicate集合生成并返回and 连接的 where条件
	             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			
			}
		};
	}
	
}
