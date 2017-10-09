package com.oa.message.web;



import java.io.File;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/resources")
public class ResourcesController {
	@RequestMapping("/upload")
	public void doFirest(MultipartFile uploadFile,HttpSession session) throws Exception {
		String filename=uploadFile.getOriginalFilename();
		String leftPath=session.getServletContext().getRealPath("/resources");
		File file=new File(leftPath,filename);
		uploadFile.transferTo(file);
	}

}
