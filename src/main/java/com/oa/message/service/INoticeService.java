package com.oa.message.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oa.message.entity.Notice;
import com.oa.message.entity.dto.NoticeDTO;



public interface INoticeService {
	public void save(Notice entity);
	public void delete(Notice entity);
	public void delete(Long id);
	public void delete(Long[] ids);
	public List<NoticeDTO> findAll();
	public Page<NoticeDTO> findAll(Pageable pageable);
}
