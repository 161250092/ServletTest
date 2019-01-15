package service;

import dao.OrderDao;
import model.Order;
import model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderManageServiceBean implements OrderManageService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public ArrayList<Order> getUserAllOrders(String account) {
        return orderDao.getUserAllOrders(account);
    }

    @Override
    public double addOrder(String account, ArrayList<ProductItem> productList) {
        return orderDao.addOrder(account,productList);
    }
}
