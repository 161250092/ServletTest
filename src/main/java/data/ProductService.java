package data;

import model.Product;

import java.util.ArrayList;

public interface ProductService {

    public ArrayList<Product> getAllProductInfo();


    public int getProductRepertory(int productId);


    public boolean stockOut(int productId,int quantity);


}
