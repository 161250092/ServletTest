package service.impl;

import factory.DaoFactory;
import model.Product;
import service.ProductManageService;

import java.util.ArrayList;

public class ProductManageServiceImpl implements ProductManageService{
    private static ProductManageService productManageService = new ProductManageServiceImpl();
    public static  ProductManageService getInstance(){return productManageService;}

    @Override
    public ArrayList<Product> getAllProductInfo() {
        return DaoFactory.getProductDao().getAllProductInfo();
    }

    @Override
    public int getProductRepertory(int productId) {
        return DaoFactory.getProductDao().getProductRepertory(productId);
    }

    @Override
    public boolean stockOut(int productId, int quantity) {
        return DaoFactory.getProductDao().stockOut(productId,quantity);
    }
}
