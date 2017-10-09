package com.oa.authority.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oa.authority.dao.IModuleDao;
import com.oa.authority.entity.Module;
import com.oa.authority.entity.dto.ModuleDTO;
import com.oa.authority.entity.dto.RoleDTO;

@Service
public class ModuleService implements IModuleService {

	@Autowired
	private IModuleDao moduleDao;

	@Override
	public void save(Module module) {
		moduleDao.save(module);
	}

	@Override
	public void delete(Module module) {
		moduleDao.delete(module);
	}

	@Override
	public void delete(Integer id) {
		moduleDao.delete(id);
	}

	@Override
	public void delete(Integer[] ids) {
		for(Integer id : ids) {
			moduleDao.delete(id);
		}
	}

	@Override
	public List<ModuleDTO> findAll() {
		List<Module> moduleList = (List<Module>)moduleDao.findAll();
		List<ModuleDTO> dtoList = new ArrayList<ModuleDTO>();
		for(Module entity : moduleList) {
			ModuleDTO dto = new ModuleDTO();
			ModuleDTO.entityToDto(dto, entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public Page<ModuleDTO> findAll(Pageable pageable) {
		Page<Module> modulePage = moduleDao.findAll(pageable);
		List<ModuleDTO> dtoList = new ArrayList<ModuleDTO>();
		for(Module entity : modulePage) {
			ModuleDTO dto = new ModuleDTO();
			ModuleDTO.entityToDto(dto, entity);
			dtoList.add(dto);
		}
		PageImpl<ModuleDTO> page = new PageImpl<ModuleDTO>(dtoList, pageable, modulePage.getTotalElements());
		return page;
	}
	
	
}
