package utils;

import model.Order;
import model.Product;
import model.ProductItem;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory(){
        try {
            Configuration config = new Configuration().configure();
            config.addAnnotatedClass(User.class);
            config.addAnnotatedClass(Product.class);
            config.addAnnotatedClass(ProductItem.class);
            config.addAnnotatedClass(Order.class);
//编程配置映射
//否则org.hibernate.MappingException: Unknown entity:
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static Session getSession(){
        return getSessionFactory().getCurrentSession();
    }


}
