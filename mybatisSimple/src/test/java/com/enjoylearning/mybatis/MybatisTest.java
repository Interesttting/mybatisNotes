package com.enjoylearning.mybatis;

import com.enjoylearning.mybatis.entity.TUser;
import com.enjoylearning.mybatis.mapper.TUserAnnoMapper;
import com.enjoylearning.mybatis.mapper.TUserMapper;
import com.enjoylearning.mybatis.mapper.TUserMapper2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisTest {
    static final String RESOURCE = "mybatis-config.xml";
    private SqlSessionFactory factory;

    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(RESOURCE);
        // 1.读取mybatis配置文件创SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }
    //简单查询
    @Test
    public void demo1(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectByPrimaryKey(1);
        System.out.println("name:"+u.getUserName());
    }
    //嵌套结果 association
    @Test
    public void demo2(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectByPrimaryKeyNestingResult(1);
        System.out.println("name:"+u.getUserName());
        System.out.println("money:"+u.getAccount().getMoney());
    }
    //简单查询 返回resultMap
    @Test
    public void demo3(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectByPrimaryKeyNestingResult(1);
        System.out.println("name:"+u.getUserName());
    }
    //嵌套查询  association
    @Test
    public void demo4(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectByPrimaryKeyNestingQuery(1);
        System.out.println("name:"+u.getUserName());
        System.out.println("money:"+u.getAccount().getMoney());
    }
    //嵌套查询 懒加载  association
    @Test
    public void demo5(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectByPrimaryKeyNestingQueryWithLazy(1);
        System.out.println("name:"+u.getUserName());
        System.out.println("---------------------------------------------");
        System.out.println("money:"+u.getAccount().getMoney());
    }
    //嵌套查询  Collection
    @Test
    public void demo6(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectByPrimaryKeyNestingQueryCollection(1);
        System.out.println("name:"+u.getUserName());
        System.out.println("---------------------------------------------");
        System.out.println("groups:"+u.getGroups());
    }
    //嵌套查询 懒加载 association and Collection
    @Test
    public void demo7(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectByPrimaryKeyNestingQueryWithLazyCollectionAndAssociation(1);
        System.out.println("name:"+u.getUserName());
        System.out.println("---------------------------------------------");
        System.out.println("money:"+u.getAccount().getMoney());
        System.out.println("groups:"+u.getGroups());
    }
    //嵌套结果 collection
    @Test
    public void demo8(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectTUserById(1);
        System.out.println("name:"+u.getUserName());
        System.out.println("---------------------------------------------");
        System.out.println("groups:"+u.getGroups());
    }

    //插入用户 通过自增主键
    @Test
    public void demo9(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser user =new TUser();
        user.setUserName("www");
        user.setSex((byte) 0);
        int t = mapper.insertByAutoIncrease(user);
        System.out.println("t:"+t);
        System.out.println(user.getId());
    }
    //插入用户 通过selectKey
    @Test
    public void demo10(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser user =new TUser();
        user.setUserName("www");
        user.setSex((byte) 0);
        int t = mapper.insertBySelectKey(user);
        System.out.println("t:"+t);
        System.out.println(user.getId());
    }
    //指定条件删除
    @Test
    public void demo11(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        int t = mapper.deleteByUserId(11);
        System.out.println("t:"+t);
    }

    //更新的例子
    @Test
    public void demo12(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser user =new TUser();
        user.setUserName("122121");
        int t = mapper.updateUser(user);
        System.out.println("影响行数："+t);
    }
    //根据动态的条件查询
    @Test
    public void demo13(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser user =new TUser();
        user.setId(1);
        user.setUserName("12");
        List<TUser> users =mapper.selectByCondition(user);
        System.out.println("size:"+users.size());
    }
    //测试动态插入一个user
    @Test
    public void demo14(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser user =new TUser();
//        user.setId(100);
        user.setUserName("12");
        int t = mapper.insertByDynamicUser(user);
        System.out.println("影响行数："+t);
    }
    //测试批量插入  (批量插入方式一)
    @Test
    public void demo15(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        List<TUser> list =new ArrayList<>();
        for(int i=0;i<3;i++){
            TUser user =new TUser();
//        user.setId(100);
            user.setUserName("test"+i);
            list.add(user);
        }
        int t =mapper.insertBatchUsers(list);
        System.out.println("影响行数："+t);
    }
    //测试where in foreach
    @Test
    public void demo16(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(8);
        List<TUser> us =mapper.selectByIds(ids);
        System.out.println(us.size());
    }
    //批量操作（可以是批量插入或者是批量更新或者混合） (批量插入方式二)
    //批量操作要考虑操作的数量，避免内存溢出或者操作超时
    @Test
    public void demo17(){
        //使用batch类型的执行器并且事务不自动提交
        SqlSession sqlSession =factory.openSession(ExecutorType.BATCH,false);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser user =new TUser();
        user.setUserName("www");
        user.setSex((byte) 0);
        int t = mapper.insertBySelectKey(user);
        int t2 = mapper.insertBySelectKey(user);
        sqlSession.commit();
        System.out.println("t:"+t);
    }
    //-----------------------------------cache---------------------------------------------------
    //一级缓存测试：对于key相同的方法调用不会重复执行sql语句。
    @Test
    public void demo18(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectByPrimaryKey(1);
        System.out.println("name:"+u.getUserName());
        TUser u2 =mapper.selectByPrimaryKey(1);
        System.out.println("u2_name:"+u2.getUserName());
    }
    //一级缓存测试：flushCache=true 则不会缓存到一级缓存。
    @Test
    public void demo19(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper mapper =sqlSession.getMapper(TUserMapper.class);
        TUser u =mapper.selectByPrimaryKeyFlushCache(1);
        System.out.println("name:"+u.getUserName());
        TUser u2 =mapper.selectByPrimaryKeyFlushCache(1);
        System.out.println("u2_name:"+u2.getUserName());
    }
    //二级缓存 相同的key不同的sqlSession共享缓存
    //mybatis二级缓存容易出现脏读，不做过多测试，只要知道通过namespace隔离，不同sqlSession的同一个mapper相同的key共享内存
    @Test
    public void demo20(){
        SqlSession sqlSession =factory.openSession(true);
        TUserMapper2 mapper =sqlSession.getMapper(TUserMapper2.class);
        TUser u =mapper.selectByPrimaryKey(1);
        System.out.println("name:"+u.getUserName());
        //似乎要sqlSession关闭后才能体现二级缓存的效果
        sqlSession.close();
        SqlSession sqlSession2 =factory.openSession(true);
        TUserMapper2 mapper2 =sqlSession2.getMapper(TUserMapper2.class);
        TUser u2 =mapper2.selectByPrimaryKey(1);
        System.out.println("u2_name:"+u2.getUserName());
        sqlSession2.close();
    }
    //-------------------mybatis注解--------------------------------------------
    //注解@select
    @Test
    public void demo21() {
        SqlSession sqlSession = factory.openSession(true);
        TUserAnnoMapper mapper = sqlSession.getMapper(TUserAnnoMapper.class);
        TUser u = mapper.selectByPrimaryKey(1);
        System.out.println("name:"+u.getUserName());
    }
    //@ResultMap
    @Test
    public void demo22() {
        SqlSession sqlSession = factory.openSession(true);
        TUserAnnoMapper mapper = sqlSession.getMapper(TUserAnnoMapper.class);
        List<TUser> list = mapper.selectAll();
        System.out.println(list.size());
    }
    //@Insert
    @Test
    public void demo23() {
        SqlSession sqlSession = factory.openSession(true);
        TUserAnnoMapper mapper = sqlSession.getMapper(TUserAnnoMapper.class);
        int t = mapper.insertUser(new TUser());
        System.out.println(t);
    }
}
