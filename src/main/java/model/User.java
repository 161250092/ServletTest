package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="user")

public class User implements Serializable{
    @Id
    @Column(name="account")
    private String id;

    @Column(name="passwd")
    private String pw;

    private String name;

    private double money;

    public User(String id, String pw, String name, double money) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.money = money;
    }

    public User(String id, String name, double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    public double getMoney() {
        return money;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
