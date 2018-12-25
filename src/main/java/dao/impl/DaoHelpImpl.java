package dao.impl;

import dao.DaoHelper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoHelpImpl implements DaoHelper {
    @Override
    public Connection getConnectionByDataSource() throws SQLException {
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

    @Override
    public void closeConnection(Connection con) {
        if(con!=null)
        {
            try
            {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closePreparedStatement(PreparedStatement stmt) {
        if(stmt!=null)
        {
            try
            {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeResult(ResultSet result) {
        if(result!=null)
        {
            try
            {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
