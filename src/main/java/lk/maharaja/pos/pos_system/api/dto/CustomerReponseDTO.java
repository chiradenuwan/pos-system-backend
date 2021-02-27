package lk.maharaja.pos.pos_system.api.dto;

import lk.maharaja.pos.pos_system.model.Orders;

import java.util.List;

public class CustomerReponseDTO {
    private int id;
    private String name;
    private String mobile;
    private String address;
    private List<OrderResponseDTO> orders;

    public CustomerReponseDTO() {
    }

    public CustomerReponseDTO(int id, String name, String mobile, String address) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
    }

    public CustomerReponseDTO(int id, String name, String mobile, String address, List<OrderResponseDTO> orders) {
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

    public List<OrderResponseDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponseDTO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "CustomerReponseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}
