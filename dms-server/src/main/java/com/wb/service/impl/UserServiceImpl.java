package com.wb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wb.mapper.UserMapper;
import com.wb.model.User;
import com.wb.service.UserService;
import com.wb.util.Result;
import com.wb.util.TokenUtil;

@Service
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 	获取用户信息
	 * @param userid
	 * @return
	 */
	public User findUserById(String userid) {
		return userMapper.selectByPrimaryKey(userid);
	}
	
	/**
	 * 	修改用户信息
	 * @param user
	 * @return
	 */
	public int modifyUserById(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 	用户注册
	 */
	public int userRegister(User user) {
		return userMapper.userRegister(user);
	}
	
	/**
	 * 	用户登录
	 */
	public Result<Map<String, String>> userLogin(User user) {
		User u = userMapper.selectByPrimaryKey(user.getUserid());
		if(u != null) {
			if(u.getPassword().equals(user.getPassword())) {
				// 获取Token
				String token = TokenUtil.createToken(user);
				Map<String, String> map = new HashMap<String, String>();
				map.put("token", token);
				map.put("username", u.getUsername());
				return Result.returnJson(1, "登录成功！", map);
			} else {
				return Result.returnJson(0, "密码输入错误！", null);
			}
		} else {
			return Result.returnJson(0, "账号不存在！", null);
		}
	}
}
