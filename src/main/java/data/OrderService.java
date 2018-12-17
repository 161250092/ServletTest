package data;

import model.Order;

import java.util.ArrayList;

public interface OrderService {
    /**
     *获取该用户所有的订单内容
     * @param account
     * @return
     */
    public ArrayList<Order> getUserAllOrders(String account);


}
