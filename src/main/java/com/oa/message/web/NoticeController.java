package com.oa.message.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.message.entity.Notice;
import com.oa.message.service.INoticeService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;






@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private INoticeService noticeService;
	public @ResponseBody List<Notice> findAll()
	{
		return noticeService.findAll();
	}
	
	@RequestMapping("/findPage")
	public @ResponseBody Page<Notice> findAll(ExtjsPageable pageable)
	{
		return noticeService.findAll(pageable.getPageable());
	}
	
	@PostMapping("/saveOrUpdate")
	public @ResponseBody ExtjsAjaxResult saveOrUpdate(Notice notice)
	{
		try {
			notice.setNoticeTime(new Date());
			noticeService.save(notice);
			return new ExtjsAjaxResult(true,"success");
		}catch(Exception e){
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"false");
		}
		
	}
	
	@PostMapping("/delete")
	public @ResponseBody ExtjsAjaxResult delete(Long[] ids)
	{
		try {
			 noticeService.delete(ids);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
}

	
}
