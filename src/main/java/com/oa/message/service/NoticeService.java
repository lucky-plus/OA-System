package com.oa.message.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.message.dao.INoticeDao;
import com.oa.message.entity.Notice;



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
	public List<Notice> findAll() {
		return (List<Notice>) noticeDao.findAll();
	}
	@Override
	public Page<Notice> findAll(Pageable pageable) {
		return noticeDao.findAll(pageable);
	}

}
