package com.oa.staff.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/staff")
public class StaffController {
	
	@RequestMapping("hello")
	public @ResponseBody String hello() {
		return "hello";
	}
	
}
