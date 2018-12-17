package data;

import model.Order;
import model.Product;
import model.ProductItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderServiceImpl implements  OrderService {
    @Override
    public ArrayList<Order> getUserAllOrders(String account) {
        ArrayList<Order>  orders = new ArrayList<Order>();
        ArrayList<Order> orderList = this.getAllOrderSimpleInfo(account);
        for(int i=0;i<orderList.size();i++){
            int orderId = orderList.get(i).getOrderId();
            ArrayList<ProductItem>  productList = this.getProductList(orderId);
            double total = 0;
            for(ProductItem item:productList){
                total +=item.getTotalPrice();
            }
            Date time =  orderList.get(i).getOrderTime();
            Order order = new Order(orderId,account, time ,productList,total);
            orders.add(order);
        }
        return orders;
    }

    public ArrayList<ProductItem>  getProductList(int orderId){

        ArrayList<ProductItem>  productList = new ArrayList<ProductItem>();

        String sql = "select l.productId,l.quantity,p.productName,p.price,p.repertory from product as p,product_list as l where p.productId = l.productId  and orderId=?";
        try {
            Connection conn = new MySQLConnector().getConnectionByDataSource();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,orderId);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                int  productId = rs.getInt("productId");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int repertory = rs.getInt("repertory");
                boolean oos = false;
                if(quantity>=repertory)
                    oos = true;

                ProductItem item = new ProductItem(orderId,productId,productName,quantity,price,quantity*price,oos);
                productList.add(item);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public ArrayList<Order>  getAllOrderSimpleInfo(String account){
        ArrayList<Order> orderList = new ArrayList<Order>();

        String sql = "select orderId,orderTime from `order` where account=?";

        try {
            Connection conn = new MySQLConnector().getConnectionByDataSource();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,account);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
               int orderId = rs.getInt("orderId");
               Date time   = rs.getDate("orderTime");
               Order order = new Order(orderId,account,time);
               orderList.add(order);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }


//    public Product getProdutInfo(int productId){
//
//        Product p = null;
//        Connection conn = new MySQLConnector().getConnection("webHomework01");
//        String sql = "select * from product where productId=?";
//
//        try {
//            PreparedStatement psmt = conn.prepareStatement(sql);
//            psmt.setInt(1,productId);
//            ResultSet rs = psmt.executeQuery();
//            while(rs.next()){
//                p = new Product(productId,rs.getString("productName"),rs.getDouble("price"),rs.getInt("repertory"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return p;
//    }





}
