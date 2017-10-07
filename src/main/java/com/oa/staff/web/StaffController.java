package com.oa.staff.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.oa.staff.entity.UserInfornation;
import com.oa.staff.service.IStaffService;
import com.oa.utils.ExtjsPageable;





@Controller
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private IStaffService staffService;
	
	@RequestMapping("hello")
	public @ResponseBody String hello() {
		return "hello";
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
