package com.oa.personnel.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oa.personnel.entity.PersonelNotes;


public interface IPersonelNotesDao extends PagingAndSortingRepository<PersonelNotes, Integer> {

}
