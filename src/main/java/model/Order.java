package model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="order_tl")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String account;

    private Date orderTime;

    @OneToMany(mappedBy="order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @OrderBy("productId ASC")
    private List<ProductItem> list = new ArrayList<ProductItem>();

    private double total;

    public Order(int orderId, String account,Date orderTime, ArrayList<ProductItem> list, double total) {
        this.orderId = orderId;
        this.account = account;
        this.orderTime = orderTime;
        this.list = list;
        this.total = total;
    }

    public Order() {
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public void setList(List<ProductItem> list) {
        this.list = list;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getAccount() {
        return account;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public List<ProductItem> getList() {
        return list;
    }

    public double getTotal() {
        return total;
    }

    public Order(int orderId, String account, Date orderTime) {
        this.orderId = orderId;

        this.account = account;
        this.orderTime = orderTime;
        list = null;
        total = 0;
    }


    public void setTotal(double total) {
        this.total = total;
    }


}
