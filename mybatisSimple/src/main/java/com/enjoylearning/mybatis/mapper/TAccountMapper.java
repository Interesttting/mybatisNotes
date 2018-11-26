package com.enjoylearning.mybatis.mapper;

import com.enjoylearning.mybatis.entity.TUser;

public interface TAccountMapper {
	
    TUser selectByPrimaryKey(Integer id);
}