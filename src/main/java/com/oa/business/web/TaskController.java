package com.oa.business.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.business.entity.dto.TaskDTO;
import com.oa.business.service.ITaskService;
import com.oa.utils.ExtjsAjaxResult;
import com.oa.utils.ExtjsPageable;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private ITaskService taskService;
	

	@RequestMapping("/saveOrUpdate")
	public @ResponseBody ExtjsAjaxResult saveOrUpdate(TaskDTO taskDTO) {
		try {
			taskService.save(taskDTO);
			return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@PostMapping("/delete")
	public @ResponseBody ExtjsAjaxResult deleteTask(Integer[] ids)
	{
		try {
			taskService.delete(ids);
			return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
	@RequestMapping("/findAll")
	public @ResponseBody Page<TaskDTO> findAll(ExtjsPageable pageable) {
		return taskService.findAll(pageable.getPageable());
	}
	
	@RequestMapping("/findByCondition")
	public @ResponseBody Page<TaskDTO> findByCondition(TaskDTO taskDTO, ExtjsPageable pageable) {
		return taskService.findAll(TaskDTO.getWhereClause(taskDTO), pageable.getPageable());
	}
	
	@RequestMapping("/updateTaskState")
	public @ResponseBody ExtjsAjaxResult updateTaskState(Integer taskId, String taskState) {
		try {
			taskService.updateTaskState(taskId, taskState);
			return new ExtjsAjaxResult(true,"操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new ExtjsAjaxResult(false,"操作失败！");
		}
	}
	
}
