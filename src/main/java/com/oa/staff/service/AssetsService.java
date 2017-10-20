package com.oa.staff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.staff.dao.IAssetsDao;
import com.oa.staff.entity.Assets;
import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.AssetsDTO;
import com.oa.staff.entity.dto.PostUserDTO;


@Service
@Transactional
public class AssetsService implements IAssetsService {

	@Autowired
	private IAssetsDao assetsDao;
	
	public void setAssetsDao(IAssetsDao assetsDao) {
		this.assetsDao = assetsDao;
	}
		

	public void save(AssetsDTO dto) {
		Assets entity = new Assets();
		AssetsDTO.dtoToEntity(dto, entity);
		assetsDao.save(entity);
	}

	@Override
	public void delete(Assets entity) {
		assetsDao.delete(entity);
	}

	@Override
	public void delete(Integer assetsId) {
		assetsDao.delete(assetsId);
	}

	@Override
	public void delete(Integer[] assetsIds) {
		for(Integer assetsId : assetsIds) {
			assetsDao.delete(assetsId);
		}
	}

	
	public Assets findOne(Integer assetsId) {
		return assetsDao.findOne(assetsId);
	}

	
	public List<AssetsDTO> findAll() {
		List<Assets> assetsList = (List<Assets>) assetsDao.findAll();
		List<AssetsDTO> dtoList = new ArrayList<AssetsDTO>();
		
		for(Assets assets : assetsList) {
			AssetsDTO dto = new AssetsDTO();
			AssetsDTO.entityToDto(assets, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public Page<AssetsDTO> findAll(Pageable pageable) {
		Page<Assets> list = assetsDao.findAll(pageable);
		List<AssetsDTO> dtoList = new ArrayList<AssetsDTO>();		
		
		for(Assets assets : list.getContent()) {
			AssetsDTO dto = new AssetsDTO();
			AssetsDTO.entityToDto(assets, dto);
			dtoList.add(dto);
		}
		
		PageImpl<AssetsDTO> page = new PageImpl<AssetsDTO>(dtoList, pageable, list.getTotalElements());
		return page;
	}

	@Override
	public Page<AssetsDTO> findAll(Specification<Assets> spec, Pageable pageable) {
		Page<Assets> assetsPage = assetsDao.findAll(spec, pageable);
		//entityToDto
		List<AssetsDTO> dtoList = new ArrayList<AssetsDTO>();
		if(assetsPage != null) {
			for(Assets assets : assetsPage.getContent()) {
				AssetsDTO dto = new AssetsDTO();
				AssetsDTO.entityToDto(assets, dto);
				dtoList.add(dto);
			}
		}
		PageImpl<AssetsDTO> page = new PageImpl<AssetsDTO>(dtoList, pageable, assetsPage.getTotalElements());
		return page;
	}

	
	@Override
	public List<AssetsDTO> findAssetsByUserId(String userId) {
		List<Assets> entityList = (List<Assets>) assetsDao.findAssetsByUserId(userId);
		List<AssetsDTO> dtoList = new ArrayList<AssetsDTO>();
		for(Assets entity : entityList) {
			AssetsDTO dto = new AssetsDTO();
			AssetsDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

}
