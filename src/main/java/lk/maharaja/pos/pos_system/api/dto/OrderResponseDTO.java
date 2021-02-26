package lk.maharaja.pos.pos_system.api.dto;

import lk.maharaja.pos.pos_system.model.OrderData;

import java.sql.Date;
import java.util.List;

public class OrderResponseDTO {
    private int id;
    private Date date;
    private double totalAmount;
    private double totalDiscount;
    private List<OrderDataReponseDTO> orderData;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(int id, Date date, double totalAmount, double totalDiscount, List<OrderDataReponseDTO> orderData) {
        this.id = id;
        this.date = date;
        this.totalAmount = totalAmount;
        this.totalDiscount = totalDiscount;
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

    public List<OrderDataReponseDTO> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<OrderDataReponseDTO> orderData) {
        this.orderData = orderData;
    }

    @Override
    public String toString() {
        return "OrderResponseDTO{" +
                "id=" + id +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                ", totalDiscount=" + totalDiscount +
                ", orderData=" + orderData +
                '}';
    }
}
