package lk.maharaja.pos.pos_system.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double qty;
    private double unit_price;
    @OneToMany(mappedBy = "item")
    private List<OrderData> orderData;

    public Item() {
    }

    public Item(String name, double qty, double unit_price) {
        this.name = name;
        this.qty = qty;
        this.unit_price = unit_price;
    }

    public Item(int id, String name, double qty, double unit_price, List<OrderData> orderData) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.unit_price = unit_price;
        this.orderData = orderData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public List<OrderData> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<OrderData> orderData) {
        this.orderData = orderData;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", unit_price=" + unit_price +
                ", orderData=" + orderData +
                '}';
    }
}
