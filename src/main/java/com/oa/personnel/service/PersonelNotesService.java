package com.oa.personnel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oa.personnel.dao.IPersonelNotesDao;
import com.oa.personnel.entity.PersonelNotes;

@Service
public class PersonelNotesService implements IPersonelNotesService {

	@Autowired
	private IPersonelNotesDao personelNotesDao;
	
	@Override
	public void save(PersonelNotes personelNotes) {
		personelNotesDao.save(personelNotes);
	}

	@Override
	public void delete(Integer[] ids) {
		for(Integer id : ids) {
			personelNotesDao.delete(id);
		}
	}

	@Override
	public Page<PersonelNotes> findAll(Pageable pageable) {
		return personelNotesDao.findAll(pageable);
	}

}
