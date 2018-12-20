package data;

import data.LoginService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {
    @Override
    public int Login_in(String user, String pw) {
        System.out.println("invoke Login_in");
        int res = -1;

        String sql = "select * from user where account=? and passwd=?";
        try {
            Connection conn = new MySQLConnector().getConnectionByDataSource();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,user);
            psmt.setString(2,pw);
            ResultSet   rs = psmt.executeQuery();
            String account = "";
            while(rs.next()) {
                account = rs.getString("account");
                String passwd = rs.getString("passwd");
                System.out.println("帐号" + account);
                System.out.println(passwd);
            }
            if(account.equals(""))
                res = 0;
            else
                res = 1;
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean deductFare(String account, double fare) {
        String sql = "update user set money=money-? where account=?";
        try {
            Connection conn = new MySQLConnector().getConnectionByDataSource();
            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setDouble(1,fare);
            psmt.setString(2,account);
            psmt.executeUpdate();

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void accountUserNum(){
        Connection conn = new MySQLConnector().getConnection("webHomework01");
        String sql = "select * from user";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet   rs = psmt.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            System.out.println("have "+count+" user");
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }










}
