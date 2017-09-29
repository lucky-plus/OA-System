package com.oa.authority.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oa.authority.dao.IRoleDao;
import com.oa.authority.entity.Module;
import com.oa.authority.entity.Role;
import com.oa.authority.entity.dto.RoleDTO;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public void save(RoleDTO dto) {
		List<Module> modules = new ArrayList<Module>();
		
		if(dto.getModuleIds() != null) {
			for(Integer moduleId : dto.getModuleIds()) {
				Module module = new Module();
				module.setModuleId(moduleId);
				modules.add(module);
			}
		}
		
		Role entity = new Role();
		RoleDTO.dtoToEntity(dto, entity);
		roleDao.save(entity);
	}

	@Override
	public void delete(Role entity) {
		roleDao.delete(entity);
	}

	@Override
	public void delete(Integer id) {
		roleDao.delete(id);
	}

	@Override
	public void delete(Integer[] ids) {
		for(Integer id : ids) {
			roleDao.delete(id);
		}
	}

	@Override
	public List<RoleDTO> findAll() {
		List<Role> roleList = (List<Role>) roleDao.findAll();
		List<RoleDTO> dtoList = new ArrayList<RoleDTO>();
		
		for(Role entity: roleList) {
			RoleDTO dto = new RoleDTO();
			RoleDTO.entityToDto(dto, entity);
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	@Override
	public Page<RoleDTO> findAll(Pageable pageable) {
		Page<Role> rolePage = roleDao.findAll(pageable);
		List<RoleDTO> dtoList = new ArrayList<RoleDTO>();
		
		for(Role entity: rolePage.getContent()) {
			RoleDTO dto = new RoleDTO();
			RoleDTO.entityToDto(dto, entity);
			dtoList.add(dto);
		}
		
		PageImpl<RoleDTO> page = new PageImpl<RoleDTO>(dtoList, pageable, rolePage.getTotalElements());
		return page;
	}

}
