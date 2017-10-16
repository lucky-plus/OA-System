package com.oa.business.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.oa.business.entity.Task;
import com.oa.business.entity.dto.TaskDTO;


public interface ITaskService {

	public void save(TaskDTO dto);
	public void delete(Integer[] ids);
	public Page<TaskDTO> findAll(Pageable pageable);
	public Page<TaskDTO> findAll(Specification<Task> spec, Pageable pageable);
	
	public void updateTaskState(Integer taskId, String taskState);
}
