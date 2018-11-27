package com.enjoylearning.mybatis.mapper;

import com.enjoylearning.mybatis.entity.TUser;

public interface TUserMapper2 {
	//简单查询
    TUser selectByPrimaryKey(Integer id);
}