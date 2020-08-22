package com.shop.mapper;

import java.util.List;

import com.shop.domain.User;

public interface UserMapper {
 
		 User  selectUserByName(String name);
		 
		 List<User>  getUserList();
}