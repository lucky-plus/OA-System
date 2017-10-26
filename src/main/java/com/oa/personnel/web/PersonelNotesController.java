package com.oa.personnel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.personnel.entity.PersonelNotes;
import com.oa.personnel.service.IPersonelNotesService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;

@Controller
@RequestMapping("/notes")
public class PersonelNotesController {

	@Autowired
	private IPersonelNotesService personelNotesService;
	
	@RequestMapping("/save")
	public @ResponseBody ExtjsAjaxResult save(PersonelNotes personelNotes) {
		try {
			personelNotesService.save(personelNotes);
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
			personelNotesService.delete(ids);
			 return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			 e.printStackTrace();
			 return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@RequestMapping("/findAll")
	public @ResponseBody Page<PersonelNotes> findAll(ExtjsPageable pageable) {
		return personelNotesService.findAll(pageable.getPageable());
	}
	
}
