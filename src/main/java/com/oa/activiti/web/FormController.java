package com.oa.activiti.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oa.activiti.entity.formParam;
import com.oa.activiti.entity.formParamList;
import com.oa.activiti.entity.dto.formQueryDTO;
import com.oa.activiti.service.FormParamService;
import com.oa.activiti.utils.mongoDBOp;
import com.oa.utils.ExtjsPageable;

@Controller
public class FormController {
	@Autowired
	FormParamService fps;
	
	@RequestMapping("/saveFormParam")
	public void saveFormParam(List<formParam> lp) throws JsonProcessingException {
		formParamList fpl = new formParamList("未命名",lp);
		fps.save(fpl);
		mongoDBOp.save(fpl);
	}
	@RequestMapping("/findOneFormParam")
	@ResponseBody
	public formParamList showForm(int formId) throws JsonProcessingException {
		formParamList fpl= mongoDBOp.findOne(formId).iterator().next();
		return fpl;
	}
	@RequestMapping("/findFormParamListsByCondition")
	public @ResponseBody Page<formQueryDTO> findByCondition(formQueryDTO fqd,ExtjsPageable pageable){
		return fps.findAll(formQueryDTO.getWhereClause(fqd), pageable.getPageable());
	}
	@RequestMapping("/findFormParamLists")
	public @ResponseBody Page<formQueryDTO> findAll(){
		return fps.findAll();
	}
}
