package com.oa.business.entity.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.business.entity.Contract;
import com.oa.staff.entity.UserInfornation;

public class ContractDTO {
	private Integer contractId;
	private String Company;//合作公司
	private String contractFile;//合同文件
	private Date contractDate;//合同日期
	private String contractNum;//合同编号
	private String contractState;//合同状态
	
	private String userId;//维护关联关系
	private String userName;
	
	
	public static void  dtoToEntity(ContractDTO dto,Contract entity) {
		BeanUtils.copyProperties(dto, entity);
		String userId = dto.getUserId();
		
		if(userId!=null && !"".equals(userId.trim())) {
			UserInfornation user = new UserInfornation();
			user.setUserId(userId);//关联关系维护
			entity.setUser(user);
		}
	}
	
	public static void  entityToDto(Contract entity , ContractDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		
		UserInfornation user = entity.getUser();
		if(user!=null) {
			dto.setUserId(user.getUserId());
			dto.setUserName(user.getUserName());

		}
		
	}
	
	
	
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
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
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
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
