package service;

import model.Order;
import model.ProductItem;

import java.util.ArrayList;

public interface OrderManageService {
    public ArrayList<Order> getUserAllOrders(String account);


    /**
     *
     * @param account
     * @param productList
     * @return
     */
    public  double addOrder(String account, ArrayList<ProductItem> productList);


}
