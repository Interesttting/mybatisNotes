package com.enjoylearning.mybatis.mapper;

import com.enjoylearning.mybatis.entity.TUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TUserAnnoMapper {
    @Results(id = "user",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "realName",column = "real_name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "mobile",column = "mobile"),
            @Result(property = "email",column = "email"),
            @Result(property = "note",column = "note"),
            @Result(property = "positionId",column = "position_id"),
    })
	@Select("select id, user_name , real_name , sex, mobile, email, note, position_id from t_user where id = #{id}")
    TUser selectByPrimaryKey(Integer id);

    @ResultMap("user")
    @Select("select id, user_name , real_name , sex, mobile, email, note, position_id from t_user")
    List<TUser> selectAll();

//    @SelectKey(before=true,keyProperty="userid",resultType=int.class,statement="select nvl(max(userid),0)+1 from myuser")
    @Insert("insert into t_user (id, user_name , real_name , sex, mobile, email, note, position_id)values " +
            "(#{id},#{userName},#{realName},#{sex},#{mobile},#{email},#{note},#{positionId})")
    @Options( useGeneratedKeys = true,keyProperty ="id" )
    int insertUser(TUser user);
}