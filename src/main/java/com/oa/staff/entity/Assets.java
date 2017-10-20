package com.oa.staff.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_assets")
public class Assets {

	private Integer assetsId;
	private String assetsNumber; //资产编号
	private String assetsName;   //资产名称
	private String assetsType;//资产类型
	private Double assetsPrice;  //资产估价
	private String assetsState;  //资产状态
	private Date assetsUsedTime; //使用时间
	private UserInfornation user;//外键关联到用户
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getAssetsId() {
		return assetsId;
	}
	public String getAssetsName() {
		return assetsName;
	}
	public String getAssetsType() {
		return assetsType;
	}
	public Double getAssetsPrice() {
		return assetsPrice;
	}
	public String getAssetsState() {
		return assetsState;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	public UserInfornation getUser() {
		return user;
	}	
	
	public String getAssetsNumber() {
		return assetsNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getAssetsUsedTime() {
		return assetsUsedTime;
	}
	
	public void setAssetsId(Integer assetsId) {
		this.assetsId = assetsId;
	}
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	public void setAssetsType(String assetsType) {
		this.assetsType = assetsType;
	}
	public void setAssetsPrice(Double assetsPrice) {
		this.assetsPrice = assetsPrice;
	}
	public void setAssetsState(String assetsState) {
		this.assetsState = assetsState;
	}
	public void setUser(UserInfornation user) {
		this.user = user;
	}			
	public void setAssetsNumber(String assetsNumber) {
		this.assetsNumber = assetsNumber;
	}
	public void setAssetsUsedTime(Date assetsUsedTime) {
		this.assetsUsedTime = assetsUsedTime;
	}
	@Override
	public String toString() {
		return "Assets [assetsId=" + assetsId + ", assetsNumber=" + assetsNumber + ", assetsName=" + assetsName
				+ ", assetsType=" + assetsType + ", assetsPrice=" + assetsPrice + ", assetsState=" + assetsState
				+ ", assetsUsedTime=" + assetsUsedTime + ", user=" + user + "]";
	}
	
}
