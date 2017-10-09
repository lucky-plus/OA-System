package com.oa.message.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.message.dao.IResourcesDao;
import com.oa.message.entity.Resources;

import com.oa.message.entity.dto.ResourcesDTO;

@Service
@Transactional
public class ResourcesService implements IResourcesService {

	@Autowired
	private IResourcesDao resourcesDao;
	
	public void setNoticeDao(IResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
	}
	public void save(ResourcesDTO dto) {
		Resources entity = new Resources();
		ResourcesDTO.dtoToEntity(dto, entity);
		resourcesDao.save(entity);
	}

	@Override
	public void delete(Resources entity) {
		resourcesDao.delete(entity);
		
	}

	@Override
	public void delete(Integer id) {
		resourcesDao.delete(id);
		
	}

	@Override
	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			resourcesDao.delete(id);
		}
		
	}
	
	@Override
	public List<ResourcesDTO> findAll() {
		List<Resources> resourcesList = (List<Resources>) resourcesDao.findAll();
		List<ResourcesDTO> dtoList = new ArrayList<ResourcesDTO>();
		
		for(Resources resources : resourcesList) {
			ResourcesDTO dto = new ResourcesDTO();
			ResourcesDTO.entityToDto(resources, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	@Override
	public Page<ResourcesDTO> findAll(Pageable pageable) {
		Page<Resources> list = resourcesDao.findAll(pageable);
		List<ResourcesDTO> dtoList = new ArrayList<ResourcesDTO>();
		
		for(Resources resources : list.getContent()) {
			ResourcesDTO dto = new ResourcesDTO();
			ResourcesDTO.entityToDto(resources, dto);
			dtoList.add(dto);
		}
		
		PageImpl<ResourcesDTO> page = new PageImpl<ResourcesDTO>(dtoList, pageable, list.getTotalElements());
		return page;
	}


}
