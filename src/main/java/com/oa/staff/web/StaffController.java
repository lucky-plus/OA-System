package com.oa.staff.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oa.staff.entity.dto.PostUserDTO;
import com.oa.staff.entity.dto.TaskUserDTO;
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
	public @ResponseBody Page<PostUserDTO> findAll(ExtjsPageable pageable)
	{
		pageable.setSort("userId");
		return staffService.findAll(pageable.getPageable());
	}
	
	@RequestMapping("/findByPage")
	public @ResponseBody Page<PostUserDTO> findAll(PostUserDTO  userPostDTO ,ExtjsPageable pageable){
		return staffService.findAll(PostUserDTO.getWhereClause(userPostDTO), pageable.getPageable());
	}
	
	@PostMapping("/saveOrUpdate")
	public @ResponseBody ExtjsAjaxResult saveOrUpdate(PostUserDTO  userPostDTO)
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
	
	@RequestMapping("/findTaskUser")
	public @ResponseBody List<TaskUserDTO> findTaskUser(Integer roleLevel) {
		return staffService.findTaskUser(roleLevel);
	}
	
	@RequestMapping("/findAllTaskUser")
	public @ResponseBody List<TaskUserDTO> findAllTaskUser() {
		return staffService.findAllTaskUser();
	}
	
	@RequestMapping("/findUserByUserId")
	public @ResponseBody PostUserDTO findUserByUserId(String userId) {
		return staffService.findUserByUserId(userId);
	}

	@RequestMapping("/updatePicture")
	public @ResponseBody ExtjsAjaxResult updatePicture(MultipartFile pictureFile, String userId, HttpServletRequest request) {
		try {
			
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String newFileName = date.trim()+userId;
			String filePath = request.getSession().getServletContext().getRealPath("/resources/images/user-profile");
		
			pictureFile.transferTo(new File(filePath+newFileName));
			staffService.updatePictureFileName(userId, newFileName);

			return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
}
