package dao.bean;

import org.springframework.stereotype.Repository;
import utils.HibernateUtil;
import dao.ProductDao;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDaoBean implements ProductDao{
    private static ProductDaoBean productDaoBean = new ProductDaoBean();
    public static ProductDaoBean getInstance(){return productDaoBean;}

    @Override
    public ArrayList<Product> getAllProductInfo() {
        Session session = HibernateUtil.getSession();
        Transaction tx= session.beginTransaction();

        Query query = session.createQuery(" from Product p order by productId asc");
        List products = query.getResultList();

System.out.println("JPA从数据找到"+products.size()+"个商品");
        ArrayList<Product> res = new ArrayList<>();
        for (Object aList : products) {
            Product product = (Product) aList;
            res.add(product);
        }
        tx.commit();
        return res;

    }

    @Override
    public int getProductRepertory(int productId) {
        Session session = HibernateUtil.getSession();
        Transaction tx= session.beginTransaction();

        Product product = session.find(Product.class,productId);

        tx.commit();
        return product.getRepertory();
    }

    @Override
    public boolean stockOut(int productId, int quantity) {
        Session session = HibernateUtil.getSession();
        Transaction tx= session.beginTransaction();

        Product product = session.find(Product.class,productId);
        int left = product.getRepertory()-quantity;
        if(left>=0){
            product.setRepertory(left);
            session.save(product);
            tx.commit();
            return true;
        }
        return false;
    }
}
