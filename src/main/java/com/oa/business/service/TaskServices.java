package com.oa.business.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.oa.business.dao.ITaskDao;
import com.oa.business.entity.Task;
import com.oa.business.entity.dto.TaskDTO;

/*
 * 如果命名为TaskService会报错，可能是因为spring容器中已经存在命名为taskService的bean了
 * 所以命名为TaskServices
 */
@Service
public class TaskServices implements ITaskService {

	@Autowired
	private ITaskDao taskDao;
	
	@Override
	public void save(TaskDTO dto) {
		Task entity = new Task();
		TaskDTO.dtoToEntity(dto, entity);
		if(entity.getTaskId() == null) {
			entity.setCreateDate(new Date());
			entity.setTaskState("未完成");
		}
		taskDao.save(entity);
	}

	@Override
	public void delete(Integer[] ids) {
		for(Integer id : ids) {
			taskDao.delete(id);
		}
	}

	@Override
	public Page<TaskDTO> findAll(Pageable pageable) {
		Page<Task> taskPage = taskDao.findAll(pageable);
		List<TaskDTO> dtoList = new ArrayList<TaskDTO>();
		for(Task entity : taskPage.getContent()) {
			TaskDTO dto = new TaskDTO();
			TaskDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		Page<TaskDTO> dtoPage = new PageImpl<TaskDTO>(dtoList, pageable, taskPage.getTotalElements());
		return dtoPage;
	}

	@Override
	public Page<TaskDTO> findAll(Specification<Task> spec, Pageable pageable) {
		Page<Task> taskPage = taskDao.findAll(spec, pageable);
		List<TaskDTO> dtoList = new ArrayList<TaskDTO>();
		for(Task entity : taskPage.getContent()) {
			TaskDTO dto = new TaskDTO();
			TaskDTO.entityToDto(entity, dto);
			dtoList.add(dto);
		}
		Page<TaskDTO> dtoPage = new PageImpl<TaskDTO>(dtoList, pageable, taskPage.getTotalElements());
		return dtoPage;
	}

	@Override
	public void updateTaskState(Integer taskId, String taskState) {
		if("已完成".equals(taskState)) {
			StringBuffer sb = new StringBuffer();
			sb.append("<font color=\"green\">"+taskState+"</font>");
			Date completeDate = new Date();
			taskDao.updateTaskState(taskId, sb.toString(), completeDate);
		}else if("已终止".equals(taskState)) {
			StringBuffer sb = new StringBuffer();
			sb.append("<font color=\"red\">"+taskState+"</font>");
			taskDao.updateTaskState(taskId, sb.toString());
		}
	}

}
