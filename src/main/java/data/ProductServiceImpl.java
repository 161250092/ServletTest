package data;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductServiceImpl implements  ProductService{
    @Override
    public ArrayList<Product> getAllProductInfo(){

        ArrayList<Product> res = new ArrayList<Product>();
        Product p = null;
        String sql = "select * from product";

        try {
            Connection conn = new MySQLConnector().getConnectionByDataSource();
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                p = new Product(rs.getInt("productId"),rs.getString("productName"),rs.getDouble("price"),rs.getInt("repertory"));
                res.add(p);
            }

            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public int getProductRepertory(int productId) {
        return 0;
    }

    @Override
    public boolean stockOut(int productId, int quantity) {

        String sql = "update product set repertory=repertory-? where productId=?";

        Connection conn = null;
        try {
            conn = new MySQLConnector().getConnectionByDataSource();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,quantity);
            psmt.setInt(2,productId);
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }


}
