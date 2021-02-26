package lk.maharaja.pos.pos_system.api.dto;

import lk.maharaja.pos.pos_system.model.Item;

public class OrderDataReponseDTO {
    private int id;
    private double sub_total;
    private double unit_price;
    private double qty;
    private ItemResponseDTO item;

    public OrderDataReponseDTO() {
    }

    public OrderDataReponseDTO(int id, double sub_total, double unit_price, double qty, ItemResponseDTO item) {
        this.id = id;
        this.sub_total = sub_total;
        this.unit_price = unit_price;
        this.qty = qty;
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderDataReponseDTO{" +
                "id=" + id +
                ", sub_total=" + sub_total +
                ", unit_price=" + unit_price +
                ", qty=" + qty +
                ", item=" + item +
                '}';
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

    public ItemResponseDTO getItem() {
        return item;
    }

    public void setItem(ItemResponseDTO item) {
        this.item = item;
    }
}
