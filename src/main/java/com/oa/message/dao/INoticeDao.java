package com.oa.message.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.oa.message.entity.Notice;

@Component
public interface INoticeDao extends PagingAndSortingRepository<Notice, Integer>, JpaSpecificationExecutor<Notice> {
	
}
