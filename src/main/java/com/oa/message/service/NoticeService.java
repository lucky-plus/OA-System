package com.oa.message.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	public void save(Notice entity) {
		noticeDao.save(entity);
	}

	@Override
	public void delete(Notice entity) {
		noticeDao.delete(entity);
		
	}

	@Override
	public void delete(Long id) {
		noticeDao.delete(id);
		
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
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
		List<Notice> noticeList = (List<Notice>) noticeDao.findAll();
		List<NoticeDTO> dtoList = new ArrayList<NoticeDTO>();
		
		for(Notice notice : noticeList) {
			NoticeDTO dto = new NoticeDTO();
			NoticeDTO.entityToDto(notice, dto);
			dtoList.add(dto);
		}
		
		PageImpl<NoticeDTO> page = new PageImpl<NoticeDTO>(dtoList, pageable, dtoList.size());
		return page;
	}

}
