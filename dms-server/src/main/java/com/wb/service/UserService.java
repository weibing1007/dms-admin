package com.wb.service;

import java.util.Map;

import com.wb.model.User;
import com.wb.util.Result;

public interface UserService {
	
	User findUserById(String userid);
	
	int modifyUserById(User user);
	
	int userRegister(User user);
	
	Result<Map<String, String>> userLogin(User user);
}
