package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product_list")
public class ProductItem implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int itemId;

    int productId;

    int quantity;



    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="orderId")
    private Order order;

    @Transient
    String productName;

    public int getItemId() {
        return itemId;
    }

    public Order getOrder() {
        return order;
    }

    @Transient
    double price;

    @Transient
    double totalPrice;

    @Transient
    boolean  oos;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOos(boolean oos) {
        this.oos = oos;
    }


    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductItem(int productId, int quantity, double totalPrice,boolean oos) {
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.oos = oos;
    }

    public ProductItem(int productId, String productName, int quantity, double price, double totalPrice, boolean oos) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.oos = oos;
    }



    public ProductItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public ProductItem() {
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getPrice() {
        return price;
    }

    public boolean isOos() {
        return oos;
    }

}
