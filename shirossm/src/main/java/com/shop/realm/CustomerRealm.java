package com.shop.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.domain.User;
import com.shop.mapper.UserMapper;

public class CustomerRealm extends AuthorizingRealm{

	
	@Autowired
	private UserMapper userMapper;
	
	
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName("自定义realm");
	}
	
	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		
		String username = token.getPrincipal().toString();
		
		/*String password = "111";*/
		
		
		User user = this.userMapper.selectUserByName(username);
		
		SimpleAuthenticationInfo simpleAuthenticationInfo 
		= new SimpleAuthenticationInfo(user.getName(),user.getPassword(), this.getName());
		
		return simpleAuthenticationInfo;
	}
	
	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}



}
