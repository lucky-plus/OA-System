package com.oa.business.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.staff.entity.UserInfornation;

@Entity
@Table(name="t_contract")
public class Contract {
	private Integer contractId;
	private String contractName;//合同名称
	private String Company;//合作公司
	private String contractFile;//合同文件
	private Date contractDate;//合同日期
	private String contractNum;//合同编号
	private String contractState;//合同状态
	private UserInfornation user;		//发布者姓名
	private String pictureFileName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getContractId() {
		return contractId;
	}
	public String getCompany() {
		return Company;
	}
	public String getContractFile() {
		return contractFile;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getContractDate() {
		return contractDate;
	}
	public String getContractNum() {
		return contractNum;
	}
	public String getContractState() {
		return contractState;
	}
	@ManyToOne
	public UserInfornation getUser() {
		return user;
	}
	public String getContractName() {
		return contractName;
	}
	public String getPictureFileName() {
		return pictureFileName;
	}
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public void setContractFile(String contractFile) {
		this.contractFile = contractFile;
	}
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public void setContractState(String contractState) {
		this.contractState = contractState;
	}
	public void setUser(UserInfornation user) {
		this.user = user;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	
}
