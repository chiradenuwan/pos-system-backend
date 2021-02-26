package lk.maharaja.pos.pos_system.api.dto;

import lk.maharaja.pos.pos_system.model.Customer;

import java.sql.Date;

public class OrderRequestDTO {
    private Date date;
    private double totalAmount;
    private double totalDiscount;
    private Customer customer;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
                "date=" + date +
                ", totalAmount=" + totalAmount +
                ", totalDiscount=" + totalDiscount +
                ", customer=" + customer +
                '}';
    }
}
