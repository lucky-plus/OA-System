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

import com.oa.authority.entity.Role;
import com.oa.authority.entity.dto.RoleDTO;
import com.oa.log.entity.Log;
import com.oa.log.service.ILogService;
import com.oa.message.entity.dto.NoticeDTO;
import com.oa.message.entity.dto.ResourcesDTO;
import com.oa.staff.entity.UserInfornation;

@Component
@Aspect
public class LogAspect {
	
	Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);
	@Autowired
	private ILogService logService;
	
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
			
		}
		
	}
	
	public void appendContent2(String className, StringBuffer content, JoinPoint joinPoint, String operation, Log log) {
		
		if(className.contains("Role")) {
			content.append(operation+"了角色,角色ID为:");
		} else if(className.contains("Notice")) {
			content.append(operation+"了公共,公共ID为:");
		} else if(className.contains("Resources")) {
			content.append(operation+"了资料,资料ID为:");
		}

		Object[] params = joinPoint.getArgs();
		Integer[] ids = (Integer[]) params[0];
		for(Integer id : ids) {
			content.append(id.toString()+"、");
		}
		log.setContent(content.toString());
		logService.save(log);
	}
}
