package com.oa.authority.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.authority.entity.Role;
import com.oa.authority.entity.dto.RoleDTO;
import com.oa.authority.service.IRoleService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;

	@RequestMapping("/findPage")
	public @ResponseBody Page<RoleDTO> findAll(ExtjsPageable pageable) {
		return roleService.findAll(pageable.getPageable());
	}
	
	@RequestMapping("/findRoleByLevel")
	public @ResponseBody List<RoleDTO> findRoleByLevel(HttpSession session) {
		Integer roleLevel = (Integer) session.getAttribute("roleLevel");
		return roleService.findRoleByLevel(roleLevel);
	}
	
	@RequestMapping("/saveOrUpdate")
	public @ResponseBody ExtjsAjaxResult saveOrUpdate(RoleDTO roleDTO) {
		try {
			roleService.save(roleDTO);
			return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody ExtjsAjaxResult deleteAuthority(Integer[] ids)
	{
		try {
			roleService.delete(ids);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@RequestMapping("/unauthorized")
	public @ResponseBody ExtjsAjaxResult unauthorized() {
		return new ExtjsAjaxResult(false, "操作失败！没有该权限");
	}
	
}
