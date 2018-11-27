package com.enjoylearning.mybatis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.enjoylearning.mybatis.entity.TUser;
import com.enjoylearning.mybatis.mapper.TUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisSpringTest {

    @Resource
	private SqlSessionFactory sqlSessionFactory;



//	@Before
//	public void init() throws IOException {
//
//		String resource = "mybatis-config.xml";
//		InputStream inputStream = Resources.getResourceAsStream(resource);
//		// 1.读取mybatis配置文件创SqlSessionFactory
//		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		inputStream.close();
//	}
    @Test
    public void demo1(){
        SqlSession sqlSession =sqlSessionFactory.openSession();
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectByPrimaryKey(1);
        System.out.println("name:"+u.getUserName());
    }





}
