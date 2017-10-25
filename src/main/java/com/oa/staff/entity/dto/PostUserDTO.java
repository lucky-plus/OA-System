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
import com.oa.authority.entity.Role;
import com.oa.personnel.entity.Department;
import com.oa.personnel.entity.Post;
import com.oa.staff.entity.UserInfornation;

public class PostUserDTO {
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
	private String pictureFileName;
	
	private Integer deptId;
	private Integer roleId;
	private Integer roleLevel;

	private String deptName;
	
	private Integer postId;
	private String postName;
	
	
	
	public static void  dtoToEntity(PostUserDTO dto,UserInfornation entity) {
		BeanUtils.copyProperties(dto, entity);
		Integer postId = dto.getPostId();
		Integer roleId = dto.getRoleId();
		
		if(postId!=null) {
			Post post = new Post();
			post.setPostId(postId);;//关联关系维护
			entity.setPost(post);;
		}
		if(roleId!=null) {
			Role role = new Role();
			role.setRoleId(roleId);
			entity.setRole(role);
		}
		
	}
	
	//后台反回针对View封装DTO
	public static void  entityToDto(UserInfornation entity , PostUserDTO dto) {
		BeanUtils.copyProperties(entity, dto);
		
		Post post = entity.getPost();
		Department dept=post.getDepartment();
		Role role = entity.getRole();

		if(post!=null) {
			dto.setPostId(post.getPostId());
			dto.setPostName(post.getPostName());
			if(dept!=null) {
				dto.setDeptId(dept.getDeptId());
				dto.setDeptName(dept.getDeptName());
			}
		}
		
		if(role!=null) {
			dto.setRoleId(role.getRoleId());
		}
		
	}
	
	
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getBirthday() {
		return birthday;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
	public Integer getPostId() {
		return postId;
	}
	public String getPostName() {
		return postName;
	}
	public String getDeptName() {
		return deptName;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public String getPictureFileName() {
		return pictureFileName;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public Integer getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public static Specification<UserInfornation> getWhereClause(PostUserDTO userPostDTO){
		return new Specification<UserInfornation>() {
			@Override
			public Predicate toPredicate(Root<UserInfornation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate =  new ArrayList<Predicate>();
				if(userPostDTO.getRealName()!=null&& !"".equals(userPostDTO.getRealName().toString())) {
					Predicate p1 = cb.like(root.get("realName").as(String.class) , "%"+userPostDTO.getRealName()+"%");
					predicate.add(p1);
				}
				if(userPostDTO.getDeptName()!=null && userPostDTO.getDeptName().trim().length()>0) {
					Predicate p2 = cb.like(root.get("post").get("department").get("deptName").as(String.class) , "%"+userPostDTO.getDeptName()+"%");
					predicate.add(p2);
				}
				if(userPostDTO.getRoleLevel()!=null && !"".equals(userPostDTO.getRoleLevel().toString())) {
					Predicate p2 = cb.lessThan(root.get("role").get("roleLevel").as(Integer.class) , userPostDTO.getRoleLevel());
					predicate.add(p2);
				}
				 return cb.and(predicate.toArray(new Predicate[predicate.size()]));
			}
		};	
	}
	
}
