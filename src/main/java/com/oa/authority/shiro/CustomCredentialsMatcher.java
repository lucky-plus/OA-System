package com.oa.authority.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;


/**
 * 密码比较器
 * @author quokka
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	//密码比较的方法   token代表用户在界面输入的用户名和密码     info代表从数据库中得到加密数据
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//		Object pwd = MD5Util.getMD5Value(new String(upToken.getPassword()));
		Object pwd = new String(upToken.getPassword());
		Object dbPwd = info.getCredentials();
		return this.equals(pwd, dbPwd);
		
	}

	
}
