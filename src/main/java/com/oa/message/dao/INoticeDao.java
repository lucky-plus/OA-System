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
	
//	@Query("from Notice n where n.noticeName like %?1% and n.noticeTime between to_date(?2, yyyy-MM-dd HH:mm:ss) and to_date(?3, yyyy-MM-dd HH:mm:ss)")
	@Query("from Notice n where n.noticeName like %?1% and n.noticeTime between ?2 and ?3")
	public Page<Notice> findByCondition(String noticeName, Date beginDate, Date endDate, Pageable pageable);
	
	@Query("from Notice n where n.noticeTime between ?1 and ?2")
	public Page<Notice> findByCondition(Date beginDate, Date endDate, Pageable pageable);
	
	@Query("from Notice n where n.noticeName like %?1% and n.noticeTime >= ?2")
	public Page<Notice> findByCondition(String noticeName, Date beginDate, Pageable pageable);
	
	@Query("from Notice n where n.noticeName like %?1% and n.noticeTime <= ?2")
	public Page<Notice> findByCondition1(String noticeName, Date endDate, Pageable pageable);
	
	@Query("from Notice n where n.noticeName like %?1%")
	public Page<Notice> findByCondition(String noticeName, Pageable pageable);
	
	@Query("from Notice n where n.noticeTime >= ?1")
	public Page<Notice> findByCondition(Date beginDate, Pageable pageable);
	
	@Query("from Notice n where n.noticeTime <= ?1")
	public Page<Notice> findByCondition1(Date endDate, Pageable pageable);

	
}
