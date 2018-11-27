package com.enjoylearning.mybatis.mapper;

import com.enjoylearning.mybatis.entity.TUser;

import java.util.List;

public interface TUserMapper2 {
	//简单查询
    TUser selectByPrimaryKey(Integer id);
}