package lk.maharaja.pos.pos_system.api.service;

import lk.maharaja.pos.pos_system.api.dto.OrderRequestDTO;
import lk.maharaja.pos.pos_system.util.StandardResponse;

public interface OrderService {
    StandardResponse save(OrderRequestDTO orderRequestDTO);

    StandardResponse getAllOrders();

}
