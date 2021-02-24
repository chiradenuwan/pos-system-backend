package lk.maharaja.pos.pos_system.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double sub_total;
    private double unit_price;
    private double qty;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public OrderData() {
    }

    public OrderData(int id, double sub_total, double unit_price, double qty, Orders orders, Item item) {
        this.id = id;
        this.sub_total = sub_total;
        this.unit_price = unit_price;
        this.qty = qty;
        this.orders = orders;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "id=" + id +
                ", sub_total=" + sub_total +
                ", unit_price=" + unit_price +
                ", qty=" + qty +
                '}';
    }
}
