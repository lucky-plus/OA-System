package com.oa.staff.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
	public @ResponseBody Page<UserRoleDTO> findUserRole(HttpSession session, ExtjsPageable pageable) {
		Integer roleLevel = (Integer) session.getAttribute("roleLevel");
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
	public @ResponseBody Page<PostUserDTO> findAll(HttpSession session, ExtjsPageable pageable)
	{
		Integer roleLevel = (Integer) session.getAttribute("roleLevel");
		pageable.setSort("userId");
		return staffService.findAll(roleLevel, pageable.getPageable());
	}
	
	@RequestMapping("/findAddress")
	public @ResponseBody Page<PostUserDTO> findAddress(HttpSession session, ExtjsPageable pageable)
	{	
		Integer roleLevel = (Integer) session.getAttribute("roleLevel");
		return staffService.findAddress(roleLevel, pageable.getPageable());
	}
	
	@RequestMapping("/findByPage")
	public @ResponseBody Page<PostUserDTO> findAll(HttpSession session, PostUserDTO  userPostDTO ,ExtjsPageable pageable){
		Integer roleLevel = (Integer) session.getAttribute("roleLevel");
		userPostDTO.setRoleLevel(roleLevel);
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
	public @ResponseBody List<TaskUserDTO> findTaskUser(HttpSession session) {
		Integer roleLevel = (Integer) session.getAttribute("roleLevel");
		return staffService.findTaskUser(roleLevel);
	}
	
	@RequestMapping("/findAllTaskUser")
	public @ResponseBody List<TaskUserDTO> findAllTaskUser() {
		return staffService.findAllTaskUser();
	}
	
	@RequestMapping("/findUserByUserId")
	public @ResponseBody PostUserDTO findUserByUserId(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		return staffService.findUserByUserId(userId);
	}

	@RequestMapping("/updatePicture")
	public @ResponseBody ExtjsAjaxResult updatePicture(String userId, HttpServletRequest request) {
//		try {
//			
//			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//			String newFileName = date.replace(" ","-") + userId + ".png";
//			String filePath = request.getSession().getServletContext().getRealPath("/resources/images/user-profile");
//			if(pictureFile == null) {
//				System.out.println("pictureFile is null");
//			}
//			pictureFile.transferTo(new File(filePath, newFileName));
//			staffService.updatePictureFileName(userId, newFileName);
//
//			return new ExtjsAjaxResult(true,"操作成功！");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ExtjsAjaxResult(false,"操作失败！");
//		}
		try {
			HttpSession session = request.getSession();
	         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request))
	        {
	            //将request变成多部分request
	            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	           //获取multiRequest 中所有的文件名
	            Iterator iter=multiRequest.getFileNames();
	             
	            while(iter.hasNext())
	            {
	                //一次遍历所有文件
	                MultipartFile file=multiRequest.getFile(iter.next().toString());
	                if(file!=null)
	                {
	                	String filePath = request.getSession().getServletContext().getRealPath("/resources/images/user-profile");;
	                	String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
	        			System.out.println(newFileName);
	        			System.out.println(userId);
	                    File targetFile = new File(filePath, newFileName);
	                    
	                    //上传
	                    file.transferTo(targetFile); 
	                    
	                    staffService.updatePictureFileName(userId, newFileName);
	                    
	                    session.removeAttribute("pictureFileName");
	                    session.setAttribute("pictureFileName", newFileName);
	                }
	                 
	            }
	           
	        }
	        return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"操作失败！");
		}
		
	}
	
}
