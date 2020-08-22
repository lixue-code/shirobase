package com.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.domain.User;
import com.shop.mapper.UserMapper;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserMapper userMapper;
	
	//查询所有用户
	public List<User> getUserList() {
		
		return userMapper.getUserList();
		
	}

}
