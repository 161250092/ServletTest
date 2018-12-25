package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DaoHelper {
    public Connection getConnectionByDataSource() throws SQLException;

    public void closeConnection(Connection con);

    public void closePreparedStatement(PreparedStatement stmt);

    public void closeResult(ResultSet result);


}
