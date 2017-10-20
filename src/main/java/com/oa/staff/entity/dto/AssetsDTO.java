package com.oa.staff.entity.dto;

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
import com.oa.staff.entity.Assets;
import com.oa.staff.entity.UserInfornation;

public class AssetsDTO {

	private Integer assetsId;
	private String assetsNumber; //资产编号
	private String assetsName;   //资产名称
	private String assetsType;     //资产类型
	private Double assetsPrice;  //资产估价
	private Date assetsUsedTime; //使用时间
	
	private String userId; //维护关联关系
	private String userName;
	private String realName;
	
	private Date beginDate;
	private Date endDate;
	
	private Double highPrice;
	private Double lowPrice;
	
	//前台ajax、form到后台 关联关系维护
		public static void  dtoToEntity(AssetsDTO dto,Assets entity) {
			BeanUtils.copyProperties(dto, entity);
			String userId = dto.getUserId();
			
			if(userId!=null && !"".equals(userId.trim())) {
				UserInfornation user = new UserInfornation();
				user.setUserId(userId);//关联关系维护		
				entity.setUser(user);
			}		
		}
		
		//后台反回针对View封装DTO
		public static void  entityToDto(Assets entity , AssetsDTO dto) {
			BeanUtils.copyProperties(entity, dto);
			
			UserInfornation user = entity.getUser();
			if(user!=null) {
				dto.setUserId(user.getUserId());
				dto.setUserName(user.getUserName());
				dto.setRealName(user.getRealName());
			}			
		}
	
		
	public Integer getAssetsId() {
			return assetsId;
		}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getAssetsNumber() {
		return assetsNumber;
	}

	public String getAssetsName() {
		return assetsName;
	}

	public Double getAssetsPrice() {
		return assetsPrice;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getAssetsUsedTime() {
		return assetsUsedTime;
	}
	

	public String getAssetsType() {
		return assetsType;
	}

	
	public Date getBeginDate() {
		return beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}			

	public Double getHighPrice() {
		return highPrice;
	}

	public Double getLowPrice() {
		return lowPrice;
	}
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setAssetsId(Integer assetsId) {
		this.assetsId = assetsId;
	}

	public void setAssetsNumber(String assetsNumber) {
		this.assetsNumber = assetsNumber;
	}

	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}

	public void setAssetsPrice(Double assetsPrice) {
		this.assetsPrice = assetsPrice;
	}

	public void setAssetsUsedTime(Date assetsUsedTime) {
		this.assetsUsedTime = assetsUsedTime;
	}
		
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}

	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	/*
	 * 动态查询
	 * 
	private Integer assetsId;
	private String assetsNumber;    //资产编号
	private String assetsName;   	//资产名称
	private AssetsType assetsType;  //资产类型
	private Double assetsPrice;  	//资产估价
	private Date assetsUsedTime; 	//使用时间
	
	private String userId; //维护关联关系
	private String userName;
	
	private Date beginDate;
	private Date endDate;
	 */


	@SuppressWarnings("unused")
	public static Specification<Assets> getWhereClause(AssetsDTO assetsDTO) {
		return new Specification<Assets>() {
			public Predicate toPredicate(Root<Assets> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 1.声明Predicate集合
				List<Predicate> predicate = new ArrayList<>();
				// 2.根据orderQueryDTO查询条件动态添加Predicate
				
				//资产名称
				if (assetsDTO.getAssetsName() != null) {
					predicate.add(cb.like(root.get("assetsName").as(String.class),"%" +assetsDTO.getAssetsName()+"%"));
				}
				//资产编号
				if (assetsDTO.getAssetsNumber() != null) {
					predicate.add(cb.like(root.get("assetsNumber").as(String.class),"%" +assetsDTO.getAssetsNumber()+"%"));
				}
				//资产类型
				if (assetsDTO.getAssetsType() != null) {
					predicate.add(cb.like(root.get("assetsType").as(String.class),"%" +assetsDTO.getAssetsType()+"%"));
				}
				//时间间隔
				if(assetsDTO.getBeginDate()!=null) {
					 predicate.add(cb.greaterThanOrEqualTo(root.get("assetsUsedTime").as(Date.class), assetsDTO.getBeginDate()));
				 }
				 if(assetsDTO.getEndDate()!=null){
					 predicate.add(cb.lessThanOrEqualTo(root.get("assetsUsedTime").as(Date.class), assetsDTO.getEndDate()));
				 }
				//价格间隔
				if(assetsDTO.getLowPrice()!=null) {
					 predicate.add(cb.greaterThanOrEqualTo(root.get("assetsPrice").as(Double.class), assetsDTO.getLowPrice()));
				 }
				 if(assetsDTO.getHighPrice()!=null){
					 predicate.add(cb.lessThanOrEqualTo(root.get("assetsPrice").as(Double.class), assetsDTO.getHighPrice()));
				 }
						
				// 3.根据Predicate集合生成并返回and 连接的 where条件
				return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			}
		};
	}
}
