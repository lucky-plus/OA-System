package com.oa.message.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.oa.message.entity.Notice;

@Component
public interface INoticeDao extends PagingAndSortingRepository<Notice, Integer> {

}
