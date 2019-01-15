package service;

import dao.ProductDao;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductManageServiceBean implements ProductManageService {
    @Autowired
    ProductDao productDao;


    @Override
    public ArrayList<Product> getAllProductInfo() {
        return productDao.getAllProductInfo();
    }

    @Override
    public int getProductRepertory(int productId) {
        return productDao.getProductRepertory(productId);
    }

    @Override
    public boolean stockOut(int productId, int quantity) {
        return productDao.stockOut(productId,quantity);
    }
}
