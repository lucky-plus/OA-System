package com.oa.authority.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oa.authority.entity.Role;
import com.oa.authority.entity.dto.RoleDTO;


public interface IRoleService {
	public void save(RoleDTO dto);
	public void delete(Role entity);
	public void delete(Integer id);
	public void delete(Integer[] ids);
	public List<RoleDTO> findAll();
	public Page<RoleDTO> findAll(Pageable pageable);

}
