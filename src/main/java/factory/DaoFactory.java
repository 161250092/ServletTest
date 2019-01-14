package factory;

import dao.LoginDao;
import dao.OrderDao;
import dao.ProductDao;
import dao.bean.LoginDaoBean;
import dao.bean.OrderDaoBean;
import dao.bean.ProductDaoBean;


public class DaoFactory {

    public static LoginDao getLoginDao(){return LoginDaoBean.getInstance();}

    public static OrderDao getOrderDao(){return OrderDaoBean.getInstance();}

    public static ProductDao getProductDao(){return ProductDaoBean.getInstance();}

}
