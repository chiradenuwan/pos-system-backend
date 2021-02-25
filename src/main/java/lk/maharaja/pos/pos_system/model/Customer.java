package lk.maharaja.pos.pos_system.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String mobile;
    private String address;
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    public Customer() {
    }

    public Customer(String name, String mobile, String address) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
    }

    public Customer(int id, String name, String mobile, String address, List<Orders> orders) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.orders = orders;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}
