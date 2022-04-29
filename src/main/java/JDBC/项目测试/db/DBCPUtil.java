package JDBC.项目测试.db;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPUtil {
    private static DataSource DS;
    private static final String configFile="E:/framework/commons-dbcp2-2.9.0/dbcp.properties";
    public Connection getConn(){
        Connection con=null;
        if (DS!=null){
            try {
                con=DS.getConnection();
                con.setAutoCommit(false);// 关闭自动提交
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
            return con;
        }
        return con;
    }
    public DBCPUtil(){
        initDbcp();
    }
    private static void initDbcp(){
        Properties pops=new Properties();
        try {
            pops.load(Object.class.getResourceAsStream(configFile));
            DS= BasicDataSourceFactory.createDataSource(pops);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public DBCPUtil(String connectURI){
        initDS(connectURI);
    }
    public DBCPUtil(String connectURI,String username,String pswd,String driverClass
                ,int initialSize,int maxActive,int maxIdle,int maxWait,int minIdle){
        initDS(connectURI,username,pswd,driverClass,initialSize,maxActive
                ,maxIdle,maxWait,minIdle);
    }
    public static void initDS(String connectURI){
        initDS(connectURI,"root","password","com.mysql.cj.jdbc.Driver",5,10,30,10000,1);
    }
    public static void initDS(String connectURI,String username,String pswd,String driverClass,
                              int initialSize,int maxtotal,int maxIdle,int maxWaitMillis
                                ,int minIdle){
        BasicDataSource ds=new BasicDataSource();
        ds.setDriverClassName(driverClass);
        ds.setUsername(username);
        ds.setPassword(pswd);
        ds.setUrl(connectURI);
        ds.setInitialSize(initialSize);// 初始连接数
        ds.setMaxTotal(maxtotal);
        ds.setMaxIdle(maxIdle);
        ds.setMaxWaitMillis(maxWaitMillis);
        ds.setMinIdle(minIdle);
        DS=ds;
    }
}
