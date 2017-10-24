package com.oa.authority.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.oa.staff.service.IStaffService;
import com.oa.authority.entity.Module;
import com.oa.authority.entity.Role;
import com.oa.staff.entity.UserInfornation;

/**
 * Realm域
 * @author quokka
 */
public class AuthRealm extends AuthorizingRealm {
	
	@Autowired
	private IStaffService staffService;
	
	//授权   当jsp页面出现Shiro标签时，就会执行授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		System.out.println("授权");
		UserInfornation user = (UserInfornation) pc.fromRealm(this.getName()).iterator().next();//根据realm的名字去找对应的realm
		
		List<String> permissions = new ArrayList<String>();
		List<Module> modules = user.getRole().getModules();//得到每个角色下的模块列表
		
		for(Module m :modules){
			if("用户管理".equals(m.getModelName())) {
				permissions.add("role");
			}
			if("角色--添加修改删除".equals(m.getModelName())) {
				permissions.add("roleSaveOrAdd");
				permissions.add("roleDelte");
			}
			if("公告--添加修改删除".equals(m.getModelName())) {
				permissions.add("noticSaveOrAdd");
				permissions.add("noticDelete");
			}
			if("日志中心".equals(m.getModelName())) {
				permissions.add("logFind");
			}
			if("资源--上传删除".equals(m.getModelName())) {
				permissions.add("resourcesUpload");
			}
			if("人事--员工管理&人事记录".equals(m.getModelName())) {
				permissions.add("personal");
			} else if("人事--员工管理&人事记录&部门管理".equals(m.getModelName())) {
				permissions.add("personal");
			}
			if("任务--发布任务".equals(m.getModelName())) {
				permissions.add("task");
			}
			if("资产列表".equals(m.getModelName())) {
				permissions.add("assets");
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);//添加用户的模块（权限）
		return info;
	}
	
	//认证   token 代表用户在界面输入的用户名和密码
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		System.out.println("认证");
		
		//1.向下转型
		UsernamePasswordToken upToken  = (UsernamePasswordToken) token;
		
		//2.调用业务方法，实现根据用户名查询
		UserInfornation user = staffService.findByUserName(upToken.getUsername());
		
		if(user != null) {
			AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
			return info;   //此处如果返回，就会立即进入到密码比较器
		}
		return null;//就会出现异常
	}
	
}
