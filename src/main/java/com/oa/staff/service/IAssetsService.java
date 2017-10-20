package com.oa.staff.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.oa.staff.entity.Assets;
import com.oa.staff.entity.dto.AssetsDTO;


public interface IAssetsService {

	//增加（修改）、删除、

		public void save(AssetsDTO dto);
		public void delete(Assets entity);
		public void delete(Integer assetsId);
		public void delete(Integer[] assetsIds);
		//通用查询
		public Assets findOne(Integer assetsId);
		public List<AssetsDTO> findAll();
		public Page<AssetsDTO> findAll(Pageable pageable);
		//高级查询（动态条件查询）
		public Page<AssetsDTO> findAll(Specification<Assets> spec, Pageable pageable);
		
		//根据UserId查询资产信息
		public List<AssetsDTO> findAssetsByUserId(String userId);
}
