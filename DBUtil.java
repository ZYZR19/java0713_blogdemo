package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.omg.CORBA.DATA_CONVERSION;
import org.omg.CORBA.PUBLIC_MEMBER;
import sun.util.resources.ms.CalendarData_ms_MY;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: asus
 * Date: 2020-07-13
 * Time: 21:11
 */
public class DBUtil {

    //管理数据库连接1. 建立连接 2.断开连接
    //3.jdbc中使用datasource来管理连接
    //dbutil相当于对datasource再包装 datasource每个应用程序只应该有一个实例(单例)
    //单例模式的实现 有饿汉和懒汉 此处使用懒汉模式


    private static volatile DataSource dataSource = null;//唯一的
    //datasourse的一些属性
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/java0713_blog?characterEncoding=utf-8&useSSL=true";
    private static final String USERNAME="root";
    private static final String PASSWORD="823102";
//创建实例的过程
    //由于懒汉模式在首次调用时,会发生线程不安全 所以1,加锁 2,双重if判定 3,volatile关键字
    public static DataSource getDataSource() {
        if(dataSource==null) {
            synchronized (DBUtil.class) {//给类加锁
                if(dataSource==null) {//首次调用才会线程不安全   双重if判定
                    dataSource = new MysqlDataSource() ;
                    //还需要给DataSourse设置一些属性
                    ((MysqlDataSource)dataSource).setURL(URL);//向下转型 设置url username password
                    ((MysqlDataSource) dataSource).setUser(USERNAME);
                    ((MysqlDataSource) dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    //通过这个方法来获取连接
    public static Connection getConnection() {
        try{
            return getDataSource().getConnection();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过这个方法来断开连接
    public static void close(Connection connection, PreparedStatement statement,
                             ResultSet resultSet) {
        try{
            if(resultSet!=null) {
                resultSet.close();
            }
            if(statement!=null) {
                statement.close();
            }
            if (connection!=null) {
                connection.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
