package dao.impl;

import dao.LoginDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {

    private static LoginDaoImpl loginDao = new LoginDaoImpl();

    public static LoginDaoImpl getInstance(){return loginDao;}

    @Override
    public int Login_in(String user, String pw) {
        System.out.println("invoke Login_in");
        int res = -1;

        String sql = "select * from user where account=? and passwd=?";
        try {
            Connection conn = new DaoHelpImpl().getConnectionByDataSource();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,user);
            psmt.setString(2,pw);
            ResultSet rs = psmt.executeQuery();
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
            Connection conn = new DaoHelpImpl().getConnectionByDataSource();
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
}
