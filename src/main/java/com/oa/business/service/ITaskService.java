package com.oa.business.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.oa.business.entity.Task;
import com.oa.business.entity.dto.TaskDTO;


public interface ITaskService {

	public void save(TaskDTO dto) throws MessagingException, IOException;
	public void delete(Integer[] ids);
	public Page<TaskDTO> findAll(Pageable pageable);
	public Page<TaskDTO> findAll(Specification<Task> spec, Pageable pageable);
	
	public void updateTaskState(Integer taskId, String taskState);
}
