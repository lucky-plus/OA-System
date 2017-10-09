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
import com.oa.message.entity.dto.NoticeDTO;
import com.oa.message.service.INoticeService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;






@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private INoticeService noticeService;
	public @ResponseBody List<NoticeDTO> findAll()
	{
		return noticeService.findAll();
	}
	
	@RequestMapping("/findPage")
	public @ResponseBody Page<NoticeDTO> findAll(ExtjsPageable pageable)
	{
		pageable.setSort("noticeId");
		return noticeService.findAll(pageable.getPageable());
	}
	
	@PostMapping("/saveOrUpdate")
	public @ResponseBody ExtjsAjaxResult saveOrUpdate(NoticeDTO noticeDTO)
	{
		try {
			noticeDTO.setNoticeTime(new Date());
			noticeService.save(noticeDTO);
			return new ExtjsAjaxResult(true,"success");
		}catch(Exception e){
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"false");
		}
		
	}
	
	@PostMapping("/delete")
	public @ResponseBody ExtjsAjaxResult delete(Integer[] ids)
	{
		try {
			 noticeService.delete(ids);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@PostMapping("/deleteone")
	public @ResponseBody ExtjsAjaxResult delete(Integer id)
	{
		try {
			 noticeService.delete(id);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	
	@RequestMapping("/findByCondition")
	public @ResponseBody Page<NoticeDTO> findByCondition(NoticeDTO noticeDTO, ExtjsPageable pageable)
	{
		return noticeService.findAll(NoticeDTO.getWhereClause(noticeDTO), pageable.getPageable());
	}
	
}
