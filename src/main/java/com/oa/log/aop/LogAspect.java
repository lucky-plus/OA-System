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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.oa.authority.entity.Role;
import com.oa.authority.entity.dto.RoleDTO;
import com.oa.log.entity.Log;
import com.oa.log.service.ILogService;
import com.oa.staff.entity.UserInfornation;

@Component
@Aspect
public class LogAspect {
	
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
		
		if(methodName.contains("save")) {
			
			String param = Arrays.toString(joinPoint.getArgs());
			
			//记录操作类型
			String operation = "添加";
			log.setOperation(operation);
			
			//拼接content
			if(param.contains("RoleDTO")) {
				Object[] params = joinPoint.getArgs();
				RoleDTO role = (RoleDTO) params[0];
				content.append(operation+"了角色："+role.getRoleName());
				log.setContent(content.toString());
				logService.save(log);
				
			} else if(param.contains("Role")) {
				Object[] params = joinPoint.getArgs();
				Role role = (Role) params[0];
				content.append(operation+"了角色："+role.getRoleName());
				log.setContent(content.toString());
				logService.save(log);
			}
			
		} else if(methodName.contains("delete")) {
			
			//记录操作类型
			String operation = "删除";
			log.setOperation(operation);
//			//拼接content
////			appendContent2(className, content, ret, operation);
			if(className.contains("Role")) {
				Object[] params = joinPoint.getArgs();
				content.append(operation+"了角色："+params[0].toString());
			}
			log.setContent(content.toString());
			logService.save(log);
		}
		
	}
	
//	public void appendContent1(StringBuffer content, Object ret, String operation) {
//		System.out.println(ret);
//		if(ret.toString().contains("RoleDTO")) {
//			RoleDTO role = (RoleDTO)ret;
//			content.append(operation+"了角色："+role.getRoleName());
//			
//		} else if(ret.toString().contains("Role")) {
//			Role role = (Role)ret;
//			content.append(operation+"了角色："+role.getRoleName());
//			
//		}
//		
//	}
//	
//	public void appendContent2(String className, StringBuffer content, Object ret, String operation) {
//		
//		if(className.contains("Role")) {
//			content.append(operation+"了角色：");
////			Integer[] ids = (Integer[])ret;
////			for(Integer id : ids) {
////				content.append(id);
////				content.append(" ");
////			}
//		}
//	}
}
