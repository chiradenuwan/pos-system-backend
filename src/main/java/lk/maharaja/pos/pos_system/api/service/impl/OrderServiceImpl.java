package lk.maharaja.pos.pos_system.api.service.impl;


import lk.maharaja.pos.pos_system.api.dao.ItemRepository;
import lk.maharaja.pos.pos_system.api.dao.OrderDataRepository;
import lk.maharaja.pos.pos_system.api.dao.OrderRepository;
import lk.maharaja.pos.pos_system.api.dto.OrderDataRequestDTO;
import lk.maharaja.pos.pos_system.api.dto.OrderRequestDTO;
import lk.maharaja.pos.pos_system.api.service.OrderService;
import lk.maharaja.pos.pos_system.common.alert.Alerts;
import lk.maharaja.pos.pos_system.model.Item;
import lk.maharaja.pos.pos_system.model.OrderData;
import lk.maharaja.pos.pos_system.model.Orders;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDataRepository orderDataRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public StandardResponse save(OrderRequestDTO orderRequestDTO) {
        try {
            Orders orders = new Orders(
                    new Date(System.currentTimeMillis()),
                    orderRequestDTO.getTotalAmount(),
                    orderRequestDTO.getTotalDiscount(),
                    orderRequestDTO.getCustomer()
            );
            System.out.println("customer : " + orders);
            Orders save = orderRepository.save(orders);
            System.out.println(save.getId());
            OrderData saveOrderData= new OrderData();
            for (OrderDataRequestDTO dto : orderRequestDTO.getItems()) {
                System.out.println(dto);
                OrderData orderData = new OrderData(
                        dto.getSub_total(),
                        dto.getUnit_price(),
                        dto.getQty(),
                        new Orders(save.getId()),
                        new Item(dto.getItem())
                );
                saveOrderData = orderDataRepository.save(orderData);
                Optional<Item> itemDetails = itemRepository.findById(dto.getItem());
                Item item = new Item(
                        dto.getItem(),
                        itemDetails.get().getName(),
                        itemDetails.get().getQty()-dto.getQty(),
                        dto.getUnit_price()
                );

                itemRepository.save(item);

            }
         System.out.println(save);
        if (save != null) {
            return new StandardResponse(200, Alerts.saveSuccess, saveOrderData);
        } else {
            return new StandardResponse(201, Alerts.saveFailed, null);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
