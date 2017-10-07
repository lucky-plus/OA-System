package com.oa.staff.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

@Entity
@Table(name="t_user")
public class UserInfornation {

	private String userId;
	private String userName;
	private String password;
	private String sex;
	private String mail;
	private String mobilePhone;
	private String idType;
	private String idNumber;
	private Date birthday;
	private String nativePlace;
	private Date onDutDate;
	private String wechatNumber;
	private String home;
	private String realName;
	private int qq_number;
	private String dept;
	
	@Id
	@Column(length=8)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getUserId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}
	public String getUserName() {
		return userName;
	}
	public String getSex() {
		return sex;
	}
	public String getMail() {
		return mail;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public String getIdType() {
		return idType;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public Date getOnDutDate() {
		return onDutDate;
	}
	public String getWechatNumber() {
		return wechatNumber;
	}
	public String getHome() {
		return home;
	}
	public String getRealName() {
		return realName;
	}
	public int getQq_number() {
		return qq_number;
	}
	public String getDept() {
		return dept;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public void setOnDutDate(Date onDutDate) {
		this.onDutDate = onDutDate;
	}
	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public void setQq_number(int qq_number) {
		this.qq_number = qq_number;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
	public static Specification<UserInfornation> getWhereClause(UserInfornation userInfornation){
		return new Specification<UserInfornation>() {
			@Override
			public Predicate toPredicate(Root<UserInfornation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate =  new ArrayList<Predicate>();
				if(userInfornation.getRealName()!=null) {
					Predicate p1 = cb.like(root.get("realName").as(String.class) , "%"+userInfornation.getRealName()+"%");
					predicate.add(p1);
				}
				if(userInfornation.getDept()!=null && userInfornation.getDept().trim().length()>0) {
					Predicate p2 = cb.like(root.get("dept").as(String.class) , "%"+userInfornation.getDept()+"%");
					predicate.add(p2);
				}
				 return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			}
		};	
	}
	
	
	
}
