package com.oa.staff.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.staff.entity.dto.UserPostDTO;
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
	public @ResponseBody Page<UserPostDTO> findAll(ExtjsPageable pageable)
	{
		pageable.setSort("userId");
		return staffService.findAll(pageable.getPageable());
	}
	
	@RequestMapping("/findByPage")
	public @ResponseBody Page<UserPostDTO> findAll(UserPostDTO  userPostDTO ,ExtjsPageable pageable){
		return staffService.findAll(UserPostDTO.getWhereClause(userPostDTO), pageable.getPageable());
	}
	
	@PostMapping("/saveOrUpdate")
	public @ResponseBody ExtjsAjaxResult saveOrUpdate(UserPostDTO  userPostDTO)
	{
		try {

			staffService.save(userPostDTO);
			return new ExtjsAjaxResult(true,"success");
		}catch(Exception e){
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"false");
		}
		
	}
	
	@PostMapping("/delete")
	public @ResponseBody ExtjsAjaxResult delete(String[] ids)
	{
		try {
			staffService.delete(ids);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@PostMapping("/deleteone")
	public @ResponseBody ExtjsAjaxResult delete(String id)
	{
		try {
			staffService.delete(id);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
}
