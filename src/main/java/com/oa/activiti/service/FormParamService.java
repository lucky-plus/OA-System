package com.oa.activiti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.oa.activiti.dao.formParamListDao;
import com.oa.activiti.entity.formParamList;
import com.oa.activiti.entity.dto.formQueryDTO;

@Service
public class FormParamService implements IFormParamListService{
	
	@Autowired
	formParamListDao fpld;
	
	@Override
	public void save(formParamList fpl) {
		// TODO Auto-generated method stub
		fpld.save(fpl);
	}

	@Override
	public void delete(formParamList fpl) {
		// TODO Auto-generated method stub
		fpld.delete(fpl);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		fpld.delete(id);
	}

	@Override
	public Page<formQueryDTO> findAll() {
		// TODO Auto-generated method stub
		Page<formParamList> result = (Page<formParamList>) fpld.findAll();
		List<formQueryDTO> list = new ArrayList<formQueryDTO>();
		for(formParamList fplt : result.getContent()) {
			formQueryDTO fqd = new formQueryDTO();
			formQueryDTO.entityToDto(fplt, fqd);
			list.add(fqd);
		}
		Page<formQueryDTO> page = new PageImpl<formQueryDTO>(list);
		return page;
	}

	@Override
	public Page<formQueryDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<formParamList> result = fpld.findAll(pageable);
		List<formQueryDTO> list = new ArrayList<formQueryDTO>();
		for(formParamList fplt : result.getContent()) {
			formQueryDTO fqd = new formQueryDTO();
			formQueryDTO.entityToDto(fplt, fqd);
			list.add(fqd);
		}
		Page<formQueryDTO> page = new PageImpl<formQueryDTO>(list, pageable, result.getTotalElements());
		return page;
		
	}
	
	@Override
	public Page<formQueryDTO> findAll(Specification<formParamList> spec, Pageable pageable){
		Page<formParamList> page = fpld.findAll(spec, pageable);
		List<formQueryDTO> dtoList = new ArrayList<formQueryDTO>();
		for(formParamList fpl : page.getContent()) {
			formQueryDTO fqd = new formQueryDTO();
			formQueryDTO.entityToDto(fpl, fqd);
			dtoList.add(fqd);
		}
		Page<formQueryDTO> result = new PageImpl<formQueryDTO>(dtoList, pageable, page.getTotalElements());
		return result;
	}
}
