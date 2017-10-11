package com.oa.authority.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oa.authority.entity.Module;


public interface IModuleService {
	public void save(Module module);
	public void delete(Module module);
	public void delete(Integer id);
	public void delete(Integer[] ids);
	public List<Module> findAll();
	public Page<Module> findAll(Pageable pageable);
}
