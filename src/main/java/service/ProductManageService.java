package service;

import model.Product;

import java.util.ArrayList;

public interface ProductManageService {
    public ArrayList<Product> getAllProductInfo();


    public int getProductRepertory(int productId);


    public boolean stockOut(int productId,int quantity);
}
