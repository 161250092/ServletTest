package factory;

import service.LoginManageService;
import service.OrderManageService;
import service.ProductManageService;
import service.impl.LoginManageServiceImpl;
import service.impl.OrderManageServiceImpl;
import service.impl.ProductManageServiceImpl;

public class ServiceFactory {

        public static LoginManageService getLoginManageService(){return LoginManageServiceImpl.getInstance();}

        public static OrderManageService getOrderManageService(){return OrderManageServiceImpl.getInstance();}

        public static ProductManageService getProductManageService(){return ProductManageServiceImpl.getInstance();}
}
