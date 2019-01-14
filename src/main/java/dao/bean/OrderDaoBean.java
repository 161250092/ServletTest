package dao.bean;

import utils.HibernateUtil;
import dao.OrderDao;
import model.Order;
import model.ProductItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDaoBean extends BaseDaoImpl implements OrderDao{
    private static OrderDaoBean orderDao = new OrderDaoBean();

    public static OrderDaoBean getInstance(){return orderDao;}

    @Override
    public ArrayList<Order> getUserAllOrders(String account) {
        return null;
    }

    @Override
    public double addOrder(String account, ArrayList<ProductItem> productList) {


        Order order = new Order();
        order.setAccount(account);


        double total = 0;
        for(ProductItem item:productList) {
            total += item.getTotalPrice();
            item.setOrder(order);
        }
        order.setTotal(total);

        order.setList(productList);

        LocalDate today = LocalDate.now();
        order.setOrderTime(Date.valueOf(today));

System.out.println("直到EM调用前都没有报错");

        super.save(order);

System.out.println("invoke em persist successfully");
        return total;


    }
}
