package lk.maharaja.pos.pos_system.api.service.impl;


import lk.maharaja.pos.pos_system.api.dao.ItemRepository;
import lk.maharaja.pos.pos_system.api.dao.OrderDataRepository;
import lk.maharaja.pos.pos_system.api.dao.OrderRepository;
import lk.maharaja.pos.pos_system.api.dto.*;
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
import java.util.ArrayList;
import java.util.List;
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
            Orders save = orderRepository.save(orders);
            OrderData saveOrderData = new OrderData();
            for (OrderDataRequestDTO dto : orderRequestDTO.getItems()) {
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
                        itemDetails.get().getQty() - dto.getQty(),
                        dto.getUnit_price()
                );

                itemRepository.save(item);

            }
            if (save != null) {
                return new StandardResponse(200, Alerts.saveSuccess, saveOrderData);
            } else {
                return new StandardResponse(201, Alerts.saveFailed, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public StandardResponse getAllOrders() {
        List<Orders> all = (List<Orders>) orderRepository.findAll();
        ArrayList<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            List<OrderDataReponseDTO> orderDataResponseDto = null;
            if (all.get(i).getOrderData().size() > 0) {
                orderDataResponseDto = getOrderDataDetails(all.get(i).getOrderData());
            }

            orderResponseDTOS.add(new OrderResponseDTO(
                    all.get(i).getId(),
                    all.get(i).getDate(),
                    all.get(i).getTotalAmount(),
                    all.get(i).getTotalDiscount(),
                    orderDataResponseDto
            ));
        }
        return new StandardResponse(200, Alerts.ok, orderResponseDTOS);
    }

    @Override
    public StandardResponse getOrderByOrderId(int orderId) {
        Optional<Orders> allById = orderRepository.findById(orderId);
        ArrayList<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();



        if (allById.isPresent()) {
            List<OrderDataReponseDTO> orderDataReponse = null;
            if (allById.get().getOrderData().size() > 0) {
                orderDataReponse = getOrderDataDetails(allById.get().getOrderData());
            }

            orderResponseDTOS.add(new OrderResponseDTO(
                    allById.get().getId(),
                    allById.get().getDate(),
                    allById.get().getTotalAmount(),
                    allById.get().getTotalDiscount(),
                    orderDataReponse
            ));
            return new StandardResponse(200, Alerts.ok, orderResponseDTOS);
        } else {
            return new StandardResponse(200, Alerts.nosuchfound, null);
        }
    }


    private List<OrderDataReponseDTO> getOrderDataDetails(List<OrderData> orderData) {
        ArrayList<OrderDataReponseDTO> orderDataReponseDTOS = new ArrayList<>();
        for (int i = 0; i < orderData.size(); i++) {
            ItemResponseDTO itemResponseDTO = getItemsInOrderData(orderData.get(i).getItem());
            OrderDataReponseDTO orderDataReponseDTO = new OrderDataReponseDTO(
                    orderData.get(i).getId(),
                    orderData.get(i).getSub_total(),
                    orderData.get(i).getUnit_price(),
                    orderData.get(i).getQty(),
                    itemResponseDTO
            );
            orderDataReponseDTOS.add(orderDataReponseDTO);
        }
        return orderDataReponseDTOS;
    }

    private ItemResponseDTO getItemsInOrderData(Item item) {
        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getQty(),
                item.getUnit_price()
        );
    }
}
