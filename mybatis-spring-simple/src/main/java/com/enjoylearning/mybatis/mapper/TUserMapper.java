package com.enjoylearning.mybatis.mapper;

import com.enjoylearning.mybatis.entity.TUser;

import java.util.List;

public interface TUserMapper {
	//简单查询
    TUser selectByPrimaryKey(Integer id);
    //简单查询
    TUser selectByPrimaryKeyFlushCache(Integer id);
    //简单查询 返回resultMap
    TUser selectByPrimaryKeyReturnResultMap(Integer id);
    //嵌套结果 association
    TUser selectByPrimaryKeyNestingResult(Integer id);
    //嵌套查询  association
    TUser selectByPrimaryKeyNestingQuery(Integer id);
    //嵌套查询 懒加载 association
    TUser selectByPrimaryKeyNestingQueryWithLazy(Integer id);
    //嵌套查询  Collection
    TUser selectByPrimaryKeyNestingQueryCollection(Integer id);
    //嵌套查询 懒加载 association and Collection
    TUser selectByPrimaryKeyNestingQueryWithLazyCollectionAndAssociation(Integer id);
    //嵌套结果 collection
    TUser selectTUserById(Integer id);
    //插入用户 通过自增主键 返回值为数据库的影响行数 返回值也可以设计成void，一般情况如果数据库操作异常也不需要返回值了，如果正常执行有没有返回值都无所谓
    int insertByAutoIncrease(TUser user);
    //插入用户 通过selectKey
    int insertBySelectKey(TUser user);
    //指定条件删除
    int deleteByUserId(int userId);
    //普通更新
    int updateUser(TUser user);
    //动态条件查询
    List<TUser> selectByCondition(TUser user);
    //动态的属性插入一个User记录
    int insertByDynamicUser(TUser user);
    // 批量插入 使用foreach标签
    int insertBatchUsers(List<TUser> users);
    //条件查询 in 使用foreach标签 参数也可以定义为int[] ids
    List<TUser> selectByIds(List<Integer> ids);

}