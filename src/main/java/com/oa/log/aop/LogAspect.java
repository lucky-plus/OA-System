package com.oa.log.aop;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.oa.activiti.entity.formParamList;
import com.oa.authority.entity.Role;
import com.oa.authority.entity.dto.RoleDTO;
import com.oa.business.entity.dto.TaskDTO;
import com.oa.business.service.ITaskService;
import com.oa.log.entity.Log;
import com.oa.log.service.ILogService;
import com.oa.message.entity.dto.NoticeDTO;
import com.oa.message.entity.dto.ResourcesDTO;
import com.oa.personnel.entity.Department;
import com.oa.personnel.entity.dto.PostDTO;
import com.oa.staff.entity.UserInfornation;
import com.oa.staff.entity.dto.AssetsDTO;
import com.oa.staff.entity.dto.PostUserDTO;

@Component
@Aspect
public class LogAspect {
	
	Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
	@Autowired
	private ILogService logService;
	@Autowired
	private ITaskService taskService;
	
	//声明切入点
	@Pointcut("execution(* com.oa.*.service.*.*(..))")
	private void myPointCut(){
	}
	
	@AfterReturning(value="myPointCut()")
	public void myAfterReturning(JoinPoint joinPoint){
		
		//获取方法名
		String methodName = joinPoint.getSignature().getName();
		//获取类名
		String className = joinPoint.getTarget().getClass().getSimpleName();
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
		HttpSession session = request.getSession();
		
		Log log = new Log();
		//记录用户信息
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		UserInfornation user = new UserInfornation();
		user.setUserId(userId);
		user.setUserName(userName);
		log.setUser(user);
		//记录时间
		log.setCreateDate(new Date());

		//操作内容
		StringBuffer content = new StringBuffer();
		//获取方法参数
		String param = Arrays.toString(joinPoint.getArgs());
		
		if(methodName.contains("save")) {

			//记录操作类型
			String operation = null;
			
			//拼接content并保存log
			appendContent1(param, content, joinPoint, operation, log);
			
		} else if(methodName.contains("delete")) {
			
			//记录操作类型
			String operation = "删除";
			log.setOperation(operation);
			//拼接content并保存log
			appendContent2(className, content, joinPoint, operation, log);
			
		} else if(methodName.contains("userRoleUpdate")) {
			
			//记录操作类型
			String operation = "修改";
			log.setOperation(operation);
			
			Object[] params = joinPoint.getArgs();
			
			content.append("修改了用户Id:"+params[0].toString()+"的权限,修改后的角色Id:"+params[1].toString());
			
			log.setContent(content.toString());
			logService.save(log);
			
		} else if(methodName.contains("updateTaskState")) {

			//记录操作类型
			String operation = "修改";
			log.setOperation(operation);
			
			Object[] params = joinPoint.getArgs();
			Integer taskId = (Integer) params[0];
			String taskState = (String) params[1];
			String taskName = taskService.findTaskNameByTaskId(taskId);
			if("已终止".equals(taskState)) {
				content.append("终止了任务："+taskName);
			} else if("已完成".equals(taskState)) {
				content.append("完成了任务："+taskName);
			}
			log.setContent(content.toString());
			logService.save(log);
			
		}
		
	}
	
	public void appendContent1(String param, StringBuffer content, JoinPoint joinPoint, String operation, Log log) {
		if(param.contains("RoleDTO")) {
			System.out.println(joinPoint.getArgs().getClass().getName());
			Object[] params = joinPoint.getArgs();
			RoleDTO role = (RoleDTO) params[0];
			if(role.getRoleId() != null) {
				operation = "修改";
				log.setOperation(operation);
			} else {
				operation = "添加";
				log.setOperation(operation);
			}
			content.append(operation+"了角色："+role.getRoleName());
			log.setContent(content.toString());
			logService.save(log);
			
		} else if(param.contains("NoticeDTO")) {
			Object[] params = joinPoint.getArgs();
			NoticeDTO notice = (NoticeDTO) params[0];
			if(notice.getNoticeId() != null) {
				operation = "修改";
				log.setOperation(operation);
			} else {
				operation = "添加";
				log.setOperation(operation);
			}
			content.append(operation+"了公告："+notice.getNoticeName());
			log.setContent(content.toString());
			logService.save(log);
			
		} else if(param.contains("ResourcesDTO")) {
			Object[] params = joinPoint.getArgs();
			ResourcesDTO resources = (ResourcesDTO) params[0];
			operation = "添加";
			log.setOperation(operation);
			content.append(operation+"了资料："+resources.getResName());
			log.setContent(content.toString());
			logService.save(log);
			
		} else if(param.contains("Department")) {
			Object[] params = joinPoint.getArgs();
			Department dept = (Department) params[0];
			operation = "添加";
			log.setOperation(operation);
			content.append(operation+"了部门："+dept.getDeptName());
			log.setContent(content.toString());
			logService.save(log);
			
		} else if(param.contains("PostDTO")) {
			Object[] params = joinPoint.getArgs();
			PostDTO post = (PostDTO) params[0];
			operation = "添加";
			log.setOperation(operation);
			content.append(operation+"了职务："+post.getPostName());
			log.setContent(content.toString());
			logService.save(log);
			
		} else if(param.contains("PostUserDTO")) {
			Object[] params = joinPoint.getArgs();
			PostUserDTO user = (PostUserDTO) params[0];
			if(user.getUserId() != null && !"".equals(user.getUserId())) {
				operation = "修改";
				log.setOperation(operation);
			} else {
				operation = "添加";
				log.setOperation(operation);
			}
			content.append(operation+"了用户："+user.getUserName()+"("+user.getRealName()+")");
			log.setContent(content.toString());
			logService.save(log);
			
		} else if(param.contains("TaskDTO")) {
			Object[] params = joinPoint.getArgs();
			TaskDTO task = (TaskDTO) params[0];
			if(task.getTaskId() != null) {
				operation = "修改";
				log.setOperation(operation);
			} else {
				operation = "添加";
				log.setOperation(operation);
			}
			content.append(operation+"了任务："+task.getTaskName());
			log.setContent(content.toString());
			logService.save(log);
			
		} else if(param.contains("AssetsDTO")) {
			Object[] params = joinPoint.getArgs();
			AssetsDTO assets = (AssetsDTO) params[0];
			if(assets.getAssetsId() != null) {
				operation = "修改";
				log.setOperation(operation);
			} else {
				operation = "添加";
				log.setOperation(operation);
			}
			content.append(operation+"了资产："+assets.getAssetsName());
			log.setContent(content.toString());
			logService.save(log);
			
		} else if(param.contains("formParamList")) {
			Object[] params = joinPoint.getArgs();
			formParamList fpl = (formParamList) params[0];
			operation = "添加";
			log.setOperation(operation);
			content.append(operation+"了表单，表单Id为："+fpl.getFormId());
			log.setContent(content.toString());
			logService.save(log);
			
		}
		
	}
	
	public void appendContent2(String className, StringBuffer content, JoinPoint joinPoint, String operation, Log log) {
		
		if(className.contains("Role")) {
			content.append(operation+"了角色,所删角色ID为:");
		} else if(className.contains("Notice")) {
			content.append(operation+"了公共,所删公共ID为:");
		} else if(className.contains("Resources")) {
			content.append(operation+"了资料,所删资料ID为:");
		} else if(className.contains("Dept")) {
			content.append(operation+"了部门,所删部门ID为:");
		} else if(className.contains("Post")) {
			content.append(operation+"了职务,所删职务ID为:");
		} else if(className.contains("Staff")) {
			content.append(operation+"了用户,所删用户ID为:");
		} else if(className.contains("Task")) {
			content.append(operation+"了任务,所删任务ID为:");
		} else if(className.contains("Asset")) {
			content.append(operation+"了资产,所删资产ID为:");
		} else if(className.contains("FormParam")) {
			content.append(operation+"了表单,所表单ID为:");
		}
		if(className.contains("Staff")) {
			Object[] params = joinPoint.getArgs();
			String[] ids = (String[]) params[0];
			for(String id : ids) {
				content.append(id+"、");
			}
		} else if(className.contains("FormParam")) {
			Object[] params = joinPoint.getArgs();
			formParamList form = (formParamList) params[0];
			content.append(String.valueOf(form.getFormId()));
		} else {
			Object[] params = joinPoint.getArgs();
			Integer[] ids = (Integer[]) params[0];
			for(Integer id : ids) {
				content.append(id.toString()+"、");
			}
		}
		log.setContent(content.toString());
		logService.save(log);
	}
}
