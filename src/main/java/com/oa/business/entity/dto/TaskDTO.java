package com.oa.business.entity.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import com.oa.business.entity.Task;
import com.oa.staff.entity.UserInfornation;

public class TaskDTO {

	private Integer taskId;
	private String taskName;
	private String taskText;
	private Date createDate;
	private String taskState;
	private String createId;		//发布者ID
	private String createName;		//发布者姓名
	private String userId;			//接收者ID
	private String userName;		//接收者姓名
	
	private Date beginDate;
	private Date endDate;
	
	public static void entityToDto(Task entity, TaskDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		if(entity.getUser() != null) {
			dto.setUserId(entity.getUser().getUserId());
			dto.setUserName(entity.getUser().getUserName());
		}
	}

	public static void dtoToEntity(TaskDTO dto, Task entity) {
		BeanUtils.copyProperties(dto, entity);
		if(dto.getUserId() != null && "".equals(dto.getUserId())) {
			UserInfornation user = new UserInfornation();
			user.setUserId(dto.getUserId());
			entity.setUser(user);
		}
	}
	
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskText() {
		return taskText;
	}
	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public static Specification<Task> getWhereClause(TaskDTO taskDTO) {
		return new Specification<Task>() {
			public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 //1.声明Predicate集合
				 List<Predicate> predicate = new ArrayList<>();
				 //2.根据DTO查询条件动态添加Predicate
				 if(taskDTO.getCreateName()!=null && !"".equals(taskDTO.getCreateName())) {
					 predicate.add(cb.like(root.get("createName").as(String.class),"%"+ taskDTO.getCreateName()+"%"));
				 }
				 if(taskDTO.getUserName()!=null && !"".equals(taskDTO.getUserName())) {
					 predicate.add(cb.like(root.get("user").get("userName").as(String.class),"%"+ taskDTO.getUserName()+"%"));
				 }
				 if(taskDTO.getBeginDate()!=null && !"".equals(taskDTO.getBeginDate().toString())) {
					 System.out.println(taskDTO.getBeginDate());
					 predicate.add(cb.greaterThanOrEqualTo(root.get("createDate").as(Date.class), taskDTO.getBeginDate()));
				 }
				 if(taskDTO.getEndDate()!=null && !"".equals(taskDTO.getEndDate().toString())){
					 System.out.println(taskDTO.getEndDate());
					 predicate.add(cb.lessThanOrEqualTo(root.get("createDate").as(Date.class), taskDTO.getEndDate()));
				 }
				 //3.根据Predicate集合生成并返回and 连接的 where条件
	             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			
			}
		};
	}
	
}
