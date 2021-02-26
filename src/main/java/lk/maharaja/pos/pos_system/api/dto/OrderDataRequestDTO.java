package lk.maharaja.pos.pos_system.api.dto;

import lk.maharaja.pos.pos_system.model.Item;

public class OrderDataRequestDTO {
    private double sub_total;
    private double unit_price;
    private double qty;
    private int item;

    @Override
    public String toString() {
        return "OrderDataRequestDTO{" +
                "sub_total=" + sub_total +
                ", unit_price=" + unit_price +
                ", qty=" + qty +
                ", item=" + item +
                '}';
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

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
}
