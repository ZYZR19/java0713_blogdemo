package model;

import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: asus
 * Date: 2020-07-15
 * Time: 18:43
 */
public class UserDao {
    //1.新增用户(注册)
    //把一个user对象插入到数据库中
    void add(User user) {
        //1.获取到数据库连接
       Connection connection = DBUtil.getConnection();
        //2 拼装sql语句
        String sql = "insert into user values (null,?,?)";
        PreparedStatement statement = null;

        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,user.getName());
            statement.setString(2,user.getPassword());
            //3执行sql语句
            int ret = statement.executeUpdate();
            if (ret!=1) {
                System.out.println("插入新用户失败!");
                return;
            }
            System.out.println("插入新用户成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//4 释放数据库连接
            DBUtil.close(connection,statement,null);
        }
    }
    //2.按名字查找用户(登录)
    public User selectByName (String name) {
        //1.和数据库建立连接
        Connection connection = DBUtil.getConnection();
        //2.拼装SQL
        String sql ="select * from user where name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement= connection.prepareStatement(sql);
            statement.setString(1,name);
            //3.执行sql
            resultSet = statement.executeQuery();
            //4.遍历结果集 语气name在数据库中不能重复
            //此处查找最多只能查出一条记录
            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,statement,resultSet);

        }
        return null;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        //1,先测试add方法
              User user = new User();
        user.setName("tz");
        user.setPassword("1234");
       userDao.add(user);
        // 2. 测试 selectByName
    User user1 = userDao.selectByName("tz");
        System.out.println(user1);

    }
}
