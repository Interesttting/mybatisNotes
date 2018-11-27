package com.enjoylearning.mybatis;

import com.enjoylearning.mybatis.entity.TUser;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JdbcTest {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "";
    @Test
    public void QueryStatement() {
        Connection connection = null;
        Statement statement =null;
        try {
            //1、加载驱动
            Class.forName(JDBC_DRIVER);
            //2、创建连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            //3、创建声明
            statement = connection.createStatement();
            String sql = "SELECT * FROM t_user ";
            //4、执行sql并得到结果集
            ResultSet resultSet = statement.executeQuery(sql);
            //5、解析结果集
            List<TUser> users =new ArrayList<>();
            while (resultSet.next()){
               TUser user =new TUser();
               user.setId(resultSet.getInt("id"));
               user.setSex(resultSet.getByte("sex"));
               user.setUserName(resultSet.getString("user_name"));
               users.add(user);
            }
            System.out.println("-------------------------");
            System.out.println("there are "+users.size()+" users in the list!");
            //6、关闭资源
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    statement=null;
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    connection=null;
                }
            }
        }

    }
    @Test
    public void QueryPreparedStatement() {
        Connection connection = null;
        PreparedStatement statement =null;
        try {
            //1、加载驱动
            Class.forName(JDBC_DRIVER);
            //2、创建连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            //3、创建预编译声明
            String sql = "SELECT * FROM t_user where user_name= ?";
            statement = connection.prepareStatement(sql);
            //4、如果有参数要设置的话
            statement.setString(1,"test");
            //5、执行sql并得到结果集
            ResultSet resultSet = statement.executeQuery();
            //6、解析结果集
            List<TUser> users =new ArrayList<>();
            while (resultSet.next()){
                TUser user =new TUser();
                user.setId(resultSet.getInt("id"));
                user.setSex(resultSet.getByte("sex"));
                user.setUserName(resultSet.getString("user_name"));
                users.add(user);
            }
            System.out.println("-------------------------");
            System.out.println("there are "+users.size()+" users in the list!");
            //7、关闭资源
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    statement=null;
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    connection=null;
                }
            }
        }

    }
    @Test
    public void UpdatePreparedStatement() {
        Connection connection = null;
        PreparedStatement statement =null;
        try {
            //1、加载驱动
            Class.forName(JDBC_DRIVER);
            //2、创建连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            //3、设置不自动提交事务
            connection.setAutoCommit(false);
            //4、创建预编译声明
            String sql = "UPDATE t_user SET mobile = ? where user_name= ?";
            statement = connection.prepareStatement(sql);
            //5、如果有参数要设置的话
            statement.setString(2,"test");
            statement.setString(1,"11111111");
            //6、执行并得到结果(返回的是影响到的行数)
            int result = statement.executeUpdate();
            //8、提交事务
            connection.commit();
            System.out.println("-------------------------");
            System.out.println("there are effect rows is "+result);
            //9、关闭资源
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    statement=null;
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    connection=null;
                }
            }
        }

    }
    @Test
    public void BatchUpdateStatement() {
        Connection connection = null;
     Statement statement =null;
        try {
            //1、加载驱动
            Class.forName(JDBC_DRIVER);
            //2、创建连接
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            //3、设置不自动提交事务
            connection.setAutoCommit(false);
            //4、声明多个sql，添加到批处理
            String sql = "UPDATE t_user SET mobile = '111111' where user_name= 'test'";
            String sql2 = "UPDATE t_user SET mobile = '111111' where user_name= 'wwww'";
            statement = connection.createStatement();
            statement.addBatch(sql);
            statement.addBatch(sql2);
            //5、执行批处理并得到结果数组(返回的是影响到的行数)
            int[] results = statement.executeBatch();
            System.out.println("-------------------------");
            System.out.println("there are effect rows is "+Arrays.toString(results));
            //6、提交事务
            connection.commit();
            //7、关闭资源
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    statement=null;
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    connection=null;
                }
            }
        }

    }
}
