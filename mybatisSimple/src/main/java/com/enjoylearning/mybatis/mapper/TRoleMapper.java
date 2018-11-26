package com.enjoylearning.mybatis.mapper;

import com.enjoylearning.mybatis.entity.TUser;

public interface TRoleMapper {
	
    TUser selectByUserId(Integer id);
}