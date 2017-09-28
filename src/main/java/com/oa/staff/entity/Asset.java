package com.oa.staff.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_asset")
public class Asset {

	private Integer assetId;
	private String assetsName;
	private String assetsType;
	private Double assetsPrice;
	private String assetsState;
	private UserInfornation user;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getAssetId() {
		return assetId;
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
	@ManyToOne
	public UserInfornation getUser() {
		return user;
	}
	
	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
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
	
}
