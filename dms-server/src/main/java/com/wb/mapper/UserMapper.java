package com.wb.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wb.model.User;

@Mapper
public interface UserMapper {
	
	// 用户注册
	int userRegister(User record);
	
    int deleteByPrimaryKey(String userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}