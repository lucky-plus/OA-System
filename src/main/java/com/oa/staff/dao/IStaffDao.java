package com.oa.staff.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.oa.message.entity.Notice;
import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.PostUserDTO;

public interface IStaffDao extends PagingAndSortingRepository<UserInfornation, String> ,JpaSpecificationExecutor<UserInfornation>{

	public UserInfornation findByUserName(String userName);
	
	@Query("from UserInfornation u where u.role.roleLevel < ?1")
	public Page<UserInfornation> findUserRole(Integer roleLevel, Pageable pageable);

	@Query("from UserInfornation t where t.role.roleLevel < ?1")
	public List<UserInfornation> findTaskUser(Integer roleLevel);
	
	@Modifying
	@Transactional
	@Query("update UserInfornation u set u.role.roleId = ?2 where u.userId = ?1")
	public void userRoleUpdate(String userId, Integer roleId);
	
	@Query("select mail from UserInfornation u where u.userId = ?1")
	public String findMailByUserId(String userId);

	@Query("select realName from UserInfornation u where u.userId = ?1")
	public String findRealNameByUserId(String userId);
	
	@Query("select userId from UserInfornation u where u.post.postId = ?1")
	public List<String> findUserIdByPostId(Integer postId);

	@Query("from UserInfornation u where u.userId = ?1")
	public UserInfornation findUserByUserId(String userId);
	
	@Modifying
	@Transactional
	@Query("update UserInfornation u set u.pictureFileName = ?2 where u.userId = ?1")
	public void updatePictureFileName(String userId, String pictureFileName);

	@Query("from UserInfornation u where u.role.roleLevel <= ?1")
	public Page<UserInfornation> findAddress(Integer roleLevel, Pageable pageable);
	
}
