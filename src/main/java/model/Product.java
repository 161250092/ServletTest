package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="product")
public class Product implements Serializable{
    @Id
    private int productId;

    private String productName;

    private double price;

    private int repertory;

    public Product() {
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRepertory(int repertory) {
        this.repertory = repertory;
    }

    public Product(int productId, String productName, double price, int repertory) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.repertory = repertory;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getRepertory() {
        return repertory;
    }
}
