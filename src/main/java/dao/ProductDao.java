package dao;

import model.Product;

import java.util.ArrayList;

public interface ProductDao {
    public ArrayList<Product> getAllProductInfo();


    public int getProductRepertory(int productId);


    public boolean stockOut(int productId,int quantity);
}
