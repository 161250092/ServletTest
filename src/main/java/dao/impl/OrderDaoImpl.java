package dao.impl;

import dao.OrderDao;

import model.Order;
import model.ProductItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDaoImpl implements OrderDao {

    private static OrderDaoImpl orderDao = new OrderDaoImpl();


    public static OrderDaoImpl getInstance(){return orderDao;}
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

    @Override
    public double addOrder(String account, ArrayList<ProductItem> productList) {
        int orderId = this.addSimpleInfoToOrder(account);
        double total=this.addProductList(orderId,productList);
        return total;
    }


    public ArrayList<ProductItem>  getProductList(int orderId){

        ArrayList<ProductItem>  productList = new ArrayList<ProductItem>();

        String sql = "select l.productId,l.quantity,p.productName,p.price,p.repertory from product as p,product_list as l where p.productId = l.productId  and orderId=?";
        try {
            Connection conn = new DaoHelpImpl().getConnectionByDataSource();
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
            Connection conn = new DaoHelpImpl().getConnectionByDataSource();
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

    public int addSimpleInfoToOrder(String account){
        int orderId = -1;
        String sql="insert into `order`(account,orderTime)values(?,?)";
        LocalDate today = LocalDate.now();
        try {
            Connection conn = new DaoHelpImpl().getConnectionByDataSource();
            PreparedStatement psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1,account);
            psmt.setDate(2,java.sql.Date.valueOf(today));
            psmt.executeUpdate();
            ResultSet rs = psmt.getGeneratedKeys();
            while(rs.next()){
                orderId = rs.getInt(1);
            }
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  orderId;
    }


    public double addProductList(int orderId,ArrayList<ProductItem> productList){
        double total = 0;
        for(ProductItem item:productList) {
            total += item.getTotalPrice();
            addSingleProductToList(orderId, item);
        }
        return total;
    }


    public boolean addSingleProductToList(int orderId,ProductItem item){

        String sql ="insert into product_list(orderId,productId,quantity)values(?,?,?)";

        try {
            Connection conn = new DaoHelpImpl().getConnectionByDataSource();
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1,orderId);
            psmt.setInt(2,item.getProductId());
            psmt.setInt(3,item.getQuantity());
            psmt.executeUpdate();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
