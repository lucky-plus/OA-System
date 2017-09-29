package com.oa.message.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.message.dao.INoticeDao;
import com.oa.message.entity.Notice;
import com.oa.message.entity.dto.NoticeDTO;



@Service
@Transactional
public class NoticeService implements INoticeService{

	@Autowired
	private INoticeDao noticeDao;
	
	public void setNoticeDao(INoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	public void save(NoticeDTO dto) {
		Notice entity = new Notice();
		NoticeDTO.dtoToEntity(dto, entity);
		noticeDao.save(entity);
	}

	@Override
	public void delete(Notice entity) {
		noticeDao.delete(entity);
		
	}

	@Override
	public void delete(Integer id) {
		noticeDao.delete(id);
		
	}

	@Override
	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			noticeDao.delete(id);
		}
		
	}
	
	@Override
	public List<NoticeDTO> findAll() {
		List<Notice> noticeList = (List<Notice>) noticeDao.findAll();
		List<NoticeDTO> dtoList = new ArrayList<NoticeDTO>();
		
		for(Notice notice : noticeList) {
			NoticeDTO dto = new NoticeDTO();
			NoticeDTO.entityToDto(notice, dto);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	@Override
	public Page<NoticeDTO> findAll(Pageable pageable) {
		Page<Notice> noticePage = noticeDao.findAll(pageable);
		List<NoticeDTO> dtoList = new ArrayList<NoticeDTO>();
		
		for(Notice notice : noticePage.getContent()) {
			NoticeDTO dto = new NoticeDTO();
			NoticeDTO.entityToDto(notice, dto);
			dtoList.add(dto);
		}
		
		PageImpl<NoticeDTO> page = new PageImpl<NoticeDTO>(dtoList, pageable, dtoList.size());
		return page;
	}
	
	@Override
	public Page<NoticeDTO> findAll(Specification<Notice> spec, Pageable pageable) {
		Page<Notice> noticePage = noticeDao.findAll(spec, pageable);
		//entityToDto
		List<NoticeDTO> dtoList = new ArrayList<NoticeDTO>();
		if(noticePage != null) {
			for(Notice notice : noticePage.getContent()) {
				NoticeDTO dto = new NoticeDTO();
				NoticeDTO.entityToDto(notice, dto);
				dtoList.add(dto);
			}
		}
		PageImpl<NoticeDTO> page = new PageImpl<NoticeDTO>(dtoList, pageable, dtoList.size());
		return page;
	}
	

}
