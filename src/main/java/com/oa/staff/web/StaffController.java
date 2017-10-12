package com.oa.staff.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.staff.entity.dto.UserRoleDTO;
import com.oa.staff.service.IStaffService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;
import com.oa.staff.entity.UserInfornation;
import com.oa.staff.service.IStaffService;
import com.oa.utils.ExtjsPageable;


@Controller
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private IStaffService staffService;
	
	@RequestMapping("/findUserRole")
	public @ResponseBody Page<UserRoleDTO> findUserRole(Integer roleLevel, ExtjsPageable pageable) {
		return staffService.findUserRole(roleLevel, pageable.getPageable());
	}
	
	@RequestMapping("/findUserRoleByCondition")
	public @ResponseBody Page<UserRoleDTO> findUserRoleByCondition(UserRoleDTO userRoleDTO, ExtjsPageable pageable) {
		return staffService.findUserRoleByCondition(UserRoleDTO.getWhereClause(userRoleDTO), pageable.getPageable());
	}
	
	@RequestMapping("/userRoleUpdate")
	public @ResponseBody ExtjsAjaxResult userRoleUpdate(String userId, Integer roleId) {
		try {
			staffService.userRoleUpdate(userId, roleId);
			return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@RequestMapping("/findPage")
	public @ResponseBody Page<UserInfornation> findAll(ExtjsPageable pageable)
	{
		pageable.setSort("userId");
		return staffService.findAll(pageable.getPageable());
	}
	
	@RequestMapping("/findByPage")
	public @ResponseBody Page<UserInfornation> findAll(UserInfornation  condetion ,ExtjsPageable pageable){
		return staffService.findAll(UserInfornation.getWhereClause(condetion), pageable.getPageable());
	}
}
