package lk.maharaja.pos.pos_system.model;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private double totalAmount;
    private double totalDiscount;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @OneToMany(mappedBy = "orders")
    private List<OrderData> orderData;

    public Orders() {
    }

    public Orders(int id, Date date, double totalAmount, double totalDiscount, Customer customer, List<OrderData> orderData) {
        this.id = id;
        this.date = date;
        this.totalAmount = totalAmount;
        this.totalDiscount = totalDiscount;
        this.customer = customer;
        this.orderData = orderData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Customer getCustomer_id() {
        return customer;
    }

    public void setCustomer_id(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public List<OrderData> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<OrderData> orderData) {
        this.orderData = orderData;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                ", totalDiscount=" + totalDiscount +
                ", customer=" + customer +
                ", orderData=" + orderData +
                '}';
    }
}
