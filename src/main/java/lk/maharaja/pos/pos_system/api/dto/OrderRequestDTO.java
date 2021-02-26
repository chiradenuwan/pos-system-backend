package lk.maharaja.pos.pos_system.api.dto;

import lk.maharaja.pos.pos_system.model.Customer;

import java.sql.Date;
import java.util.List;

public class OrderRequestDTO {
    private double totalAmount;
    private double totalDiscount;
    private Customer customer;
    private List<OrderDataRequestDTO> items;

    public List<OrderDataRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderDataRequestDTO> items) {
        this.items = items;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                ", totalAmount=" + totalAmount +
                ", totalDiscount=" + totalDiscount +
                ", customer=" + customer +
                ", items=" + items +
                '}';
    }
}
