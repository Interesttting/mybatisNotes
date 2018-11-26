package com.enjoylearning.mybatis.mapper;

import com.enjoylearning.mybatis.entity.TUser;

public interface TGroupMapper {
	
    TUser selectByUserId(Integer id);
}