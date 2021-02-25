package lk.maharaja.pos.pos_system.api.dto;

public class ItemRequestDTO {
    private String name;
    private double qty;
    private double unit_price;

    public ItemRequestDTO() {
    }

    public ItemRequestDTO(String name, double qty, double unit_price) {
        this.name = name;
        this.qty = qty;
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "ItemRequestDTO{" +
                "name='" + name + '\'' +
                ", qty=" + qty +
                ", unit_price=" + unit_price +
                '}';
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
}
