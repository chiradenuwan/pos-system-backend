package lk.maharaja.pos.pos_system.api.service;

import lk.maharaja.pos.pos_system.api.dto.CustomerRequestDTO;
import lk.maharaja.pos.pos_system.util.StandardResponse;

public interface CustomerService {
    StandardResponse save(CustomerRequestDTO customerRequestDTO);
}
