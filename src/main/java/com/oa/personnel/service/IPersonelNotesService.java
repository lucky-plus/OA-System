package com.oa.personnel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oa.personnel.entity.PersonelNotes;

public interface IPersonelNotesService {

	public void save(PersonelNotes personelNotes);
	public void delete(Integer[] ids);
	public Page<PersonelNotes> findAll(Pageable pageable);
	
}
