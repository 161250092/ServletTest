package data;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector{


    public Connection getConnection(String DataBaseName) {

        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/";

        // 数据库的用户名与密码
        final String USER = "root";
        final String PASS = "123456";

        DB_URL = DB_URL + DataBaseName + "?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false";

        Connection conn = null;
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    public  Connection getConnectionByDataSource() throws SQLException {
        Context ctx = null;
        Context context = null;
        DataSource dataSource = null;
        Connection conn = null;
        try {
            ctx = new InitialContext();
            context = (Context) ctx.lookup("java:comp/env");
            dataSource = (DataSource)context.lookup("jdbc/Mysql");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource.getConnection();
    }

}