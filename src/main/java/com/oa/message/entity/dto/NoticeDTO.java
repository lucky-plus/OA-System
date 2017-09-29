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
import com.oa.message.entity.Notice;
import com.oa.staff.entity.UserInfornation;

public class NoticeDTO 
{
	private Integer noticeId;
	private String noticeName;
	private Date noticeTime;
	private String noticeText;
	
	
	//private UserInfornation user;
	private String userId;//维护关联关系
	private String userName;

	private Date beginDate;
	private Date endDate;
	
	//前台ajax、form到后台 关联关系维护
	public static void  dtoToEntity(NoticeDTO dto,Notice entity) {
		BeanUtils.copyProperties(dto, entity);
		String userId = dto.getUserId();
		
		if(userId!=null && !"".equals(userId.trim())) {
			UserInfornation user = new UserInfornation();
			user.setUserId(userId);//关联关系维护
			
			entity.setUser(user);
		}
		
		
		
	}
	
	//后台反回针对View封装DTO
	public static void  entityToDto(Notice entity , NoticeDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		
		UserInfornation user = entity.getUser();
		if(user!=null) {
			dto.setUserId(user.getUserId());
			dto.setUserName(user.getUserName());

		}
		
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

	public Integer  getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer  noticeId) {
		this.noticeId = noticeId;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getNoticeTime() {
		return noticeTime;
	}
	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	public String getNoticeText() {
		return noticeText;
	}
	public void setNoticeText(String noticeText) {
		this.noticeText = noticeText;
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

	/**
     * 动态生成where语句
     * @param searchArticle
     * @return
     */
    @SuppressWarnings("unused")
	public static Specification<Notice> getWhereClause(NoticeDTO noticeDTO)
    {
		return new Specification<Notice>() {
			public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 //1.声明Predicate集合
				 List<Predicate> predicate = new ArrayList<>();
				 //2.根据orderQueryDTO查询条件动态添加Predicate
				 if(noticeDTO.getNoticeName()!=null) {
					 predicate.add(cb.like(root.get("noticeName").as(String.class),"%"+ noticeDTO.getNoticeName()+"%"));
				 }
				 if(noticeDTO.getBeginDate()!=null && noticeDTO.getEndDate()!=null) {
					 predicate.add(cb.between(root.get("noticeTime").as(Date.class), noticeDTO.getBeginDate(), noticeDTO.getEndDate()));
				 } else if(noticeDTO.getBeginDate()!=null && noticeDTO.getEndDate()==null) {
					 predicate.add(cb.greaterThanOrEqualTo(root.get("noticeTime").as(Date.class), noticeDTO.getBeginDate()));
				 } else if(noticeDTO.getBeginDate()==null && noticeDTO.getEndDate()!=null) {
					 predicate.add(cb.lessThanOrEqualTo(root.get("noticeTime").as(Date.class), noticeDTO.getEndDate()));
				 }

				 //3.根据Predicate集合生成并返回and 连接的 where条件
	             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			
			}
		};
    }
}
