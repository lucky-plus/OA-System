package com.oa.business.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.oa.business.dao.ITaskDao;
import com.oa.business.entity.Task;
import com.oa.business.entity.dto.TaskDTO;
import com.oa.staff.dao.IStaffDao;
import com.oa.utils.MailUtil;

/*
 * 如果命名为TaskService会报错，可能是因为spring容器中已经存在命名为taskService的bean了
 * 所以命名为TaskServices
 */
@Service
public class TaskServices implements ITaskService {

	@Autowired
	private ITaskDao taskDao;
	@Autowired
	private IStaffDao staffDao;
	
	@Override
	public void save(TaskDTO dto) throws MessagingException, IOException {
		Task entity = new Task();
		TaskDTO.dtoToEntity(dto, entity);
		if(entity.getTaskId() == null) {
			entity.setCreateDate(new Date());
			entity.setTaskState("未完成");

			String realName = staffDao.findRealNameByUserId(entity.getUser().getUserId());
			String toAddr = staffDao.findMailByUserId(entity.getUser().getUserId());
			
			StringBuffer content = new StringBuffer();
			content.append("<h1>任务——"+entity.getTaskName()+"</h1><br/>");
			content.append("<b>发布者：</b>"+entity.getCreateName()+"<br/>");
			content.append("<b>接收者：</b>"+realName+"<br/>");
			String createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getCreateDate());
			content.append("<b>发布时间：</b>"+createDate+"<br/><br/>");
			content.append("<b>任务内容：</b>"+entity.getTaskText());
			
			MailUtil.sendMessage(toAddr, "您有新的任务通知", content.toString());
			
		} else {
			String userName = taskDao.findUserNameByTaskId(entity.getTaskId());
			String realName = staffDao.findRealNameByUserId(entity.getUser().getUserId());
			if(!userName.equals(realName)) {
				String toAddr = staffDao.findMailByUserId(entity.getUser().getUserId());
				
				StringBuffer content = new StringBuffer();
				content.append("<h1>任务——"+entity.getTaskName()+"</h1><br/>");
				content.append("<b>发布者：</b>"+entity.getCreateName()+"<br/>");
				content.append("<b>接收者：</b>"+realName+"<br/>");
				String createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entity.getCreateDate());
				content.append("<b>发布时间：</b>"+createDate+"<br/><br/>");
				content.append("<b>任务内容：</b>"+entity.getTaskText());
				
				MailUtil.sendMessage(toAddr, "您有新的任务通知", content.toString());
			}
			
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

	@Override
	public String findTaskNameByTaskId(Integer taskId) {
		return taskDao.findTaskNameByTaskId(taskId);
	}

}
