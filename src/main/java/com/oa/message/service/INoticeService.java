package com.oa.message.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.oa.message.entity.Notice;
import com.oa.message.entity.dto.NoticeDTO;



public interface INoticeService {
	public void save(NoticeDTO dto);
	public void delete(Notice entity);
	public void delete(Integer id);
	public void delete(Integer[] ids);
	public List<NoticeDTO> findAll();
	public Page<NoticeDTO> findAll(Pageable pageable);
	public Page<NoticeDTO> findAll(Specification<Notice> spec, Pageable pageable);
}
