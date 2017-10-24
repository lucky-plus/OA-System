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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oa.business.entity.Contract;
import com.oa.staff.entity.UserInfornation;

public class ContractDTO {
	private Integer contractId;
	private String contractName;//合同名称
	private String Company;//合作公司
	private String contractFile;//合同文件
	private Date contractDate;//合同日期
	private String contractNum;//合同编号
	private String contractState;//合同状态
	private String pictureFileName;
	
	private String userId;//维护关联关系
	private String userName;
	
	private Date beginDate;
	private Date endDate;
	
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
	public String getContractName() {
		return contractName;
	}
	public String getPictureFileName() {
		return pictureFileName;
	}
	public Date getBeginDate() {
		return beginDate;
	}

	public Date getEndDate() {
		return endDate;
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
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
	
	 public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@SuppressWarnings("unused")
		public static Specification<Contract> getWhereClause(ContractDTO contractDTO)
	    {
			return new Specification<Contract>() {
				public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					 //1.声明Predicate集合
					 List<Predicate> predicate = new ArrayList<>();
					 //2.根据orderQueryDTO查询条件动态添加Predicate
					 if(contractDTO.getCompany()!=null) {
						 predicate.add(cb.like(root.get("company").as(String.class),"%"+ contractDTO.getCompany()+"%"));
					 }
					 if(contractDTO.getBeginDate()!=null && !"".equals(contractDTO.getBeginDate().toString())) {
						 predicate.add(cb.greaterThanOrEqualTo(root.get("contractDate").as(Date.class), contractDTO.getBeginDate()));
					 }
					 if(contractDTO.getEndDate()!=null && !"".equals(contractDTO.getEndDate().toString())){
						 predicate.add(cb.lessThanOrEqualTo(root.get("contractDate").as(Date.class), contractDTO.getEndDate()));
					 }

					 //3.根据Predicate集合生成并返回and 连接的 where条件
		             return cb.and(predicate.toArray(new Predicate[predicate.size()]));
				
				}
			};
	    }
	
}
