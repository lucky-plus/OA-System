package com.oa.authority.web;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.authority.entity.Role;
import com.oa.utils.ExtjsPageable;

@Controller
@RequestMapping("/role")
public class RoleController {

	@RequestMapping("/findPage")
	public @ResponseBody Page<Role> findAll(ExtjsPageable pageable) {
		
		return null;
	}
	
}
