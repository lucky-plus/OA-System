package com.oa.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.oa.business.dao.IContractDao;
import com.oa.business.entity.Contract;
import com.oa.business.entity.dto.ContractDTO;


@Service
public class ContractService implements IContractService{

	@Autowired
	private IContractDao contractDao;
	@Override
	public void save(ContractDTO dto) {
		Contract entity=new Contract();
		ContractDTO.dtoToEntity(dto, entity);
		contractDao.save(entity);
	}

	@Override
	public void delete(Contract entity) {
		contractDao.delete(entity);
		
	}

	@Override
	public void delete(Integer id) {
		contractDao.delete(id);
		
	}

	@Override
	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			contractDao.delete(id);
		}
		
	}

	@Override
	public Contract findOne(Integer id) {
		return contractDao.findOne(id);
	}

	@Override
	public List<ContractDTO> findAll() {
		List<Contract> contractList=(List<Contract>) contractDao.findAll();
		List<ContractDTO> dtoList=new ArrayList<ContractDTO>();
		for(Contract contract : contractList) {
			ContractDTO dto=new ContractDTO();
			ContractDTO.entityToDto(contract, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public Page<ContractDTO> findAll(Pageable pageable) {
		Page<Contract> list=contractDao.findAll(pageable);
		List<ContractDTO> dtoList=new ArrayList<ContractDTO>();
		for(Contract contract:list.getContent()) {
			ContractDTO dto=new ContractDTO();
			ContractDTO.entityToDto(contract, dto);
			dtoList.add(dto);
		}
		PageImpl<ContractDTO>page=new PageImpl<ContractDTO>(dtoList,pageable,list.getTotalElements());
		return page;
	}

	@Override
	public Page<ContractDTO> findAll(Specification<Contract> spec, Pageable pageable) {
		Page<Contract> contractPage = contractDao.findAll(spec, pageable);
		//entityToDto
		List<ContractDTO> dtoList = new ArrayList<ContractDTO>();
		if(contractPage != null) {
			for(Contract contract : contractPage.getContent()) {
				ContractDTO dto = new ContractDTO();
				ContractDTO.entityToDto(contract, dto);
				dtoList.add(dto);
			}
		}
		PageImpl<ContractDTO> page = new PageImpl<ContractDTO>(dtoList, pageable, dtoList.size());
		return page;
	}
	
	@Override
	public void updatePictureFileName(Integer contractId, String pictureFileName) {
		contractDao.updatePictureFileName(contractId, pictureFileName);
	};

}
