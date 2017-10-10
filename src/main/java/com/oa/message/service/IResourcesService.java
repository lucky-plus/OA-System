package com.oa.message.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oa.message.entity.Resources;
import com.oa.message.entity.dto.ResourcesDTO;



public interface IResourcesService {
	public void save(ResourcesDTO dto);
	public void delete(Resources entity);
	public void delete(Integer id);
	public void delete(Integer[] ids);
	public Resources findOne(Integer id);
	public List<ResourcesDTO> findAll();
	public Page<ResourcesDTO> findAll(Pageable pageable);
}
