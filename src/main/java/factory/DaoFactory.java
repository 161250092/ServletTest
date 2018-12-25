package factory;

import dao.LoginDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.impl.LoginDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.ProductDaoImpl;

public class DaoFactory {

    public static LoginDao getLoginDao(){return LoginDaoImpl.getInstance();}

    public static OrderDao getOrderDao(){return OrderDaoImpl.getInstance();}

    public static ProductDao getProductDao(){return ProductDaoImpl.getInstance();}

}
