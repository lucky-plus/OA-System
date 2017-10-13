package com.oa.personnel.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.personnel.entity.Department;
import com.oa.personnel.service.IDeptService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;

@Controller
@RequestMapping("/dept")
public class DeptController {

	@Autowired
	private IDeptService deptService;
	
	@RequestMapping("/saveOrUpdate")
	public @ResponseBody ExtjsAjaxResult saveOrUpdate(Department dept) {
		try {
			deptService.save(dept);
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
			deptService.delete(ids);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@RequestMapping("/findAll")
	public @ResponseBody Page<Department> findAll(ExtjsPageable pageable) {
		return deptService.findAll(pageable.getPageable());
	}

	@RequestMapping("/findByCondition")
	public @ResponseBody Page<Department> findAll(String deptName, ExtjsPageable pageable) {
		return deptService.findAll(deptName, pageable.getPageable());
	}
	
	@RequestMapping("/findDepts")
	public @ResponseBody List<Department> findDepts() {
		return deptService.findAll();
	}
	
}
