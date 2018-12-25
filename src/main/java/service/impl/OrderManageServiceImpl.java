package service.impl;

import factory.DaoFactory;
import model.Order;
import model.ProductItem;
import service.OrderManageService;

import java.util.ArrayList;

public class OrderManageServiceImpl implements OrderManageService{
    private static OrderManageService orderManageService = new OrderManageServiceImpl();
    public static OrderManageService getInstance(){return orderManageService;}


    @Override
    public ArrayList<Order> getUserAllOrders(String account) {
        return DaoFactory.getOrderDao().getUserAllOrders(account);
    }

    @Override
    public double addOrder(String account, ArrayList<ProductItem> productList) {
        return DaoFactory.getOrderDao().addOrder(account,productList);
    }
}
