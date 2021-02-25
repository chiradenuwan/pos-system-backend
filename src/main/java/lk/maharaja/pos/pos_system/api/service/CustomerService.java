package lk.maharaja.pos.pos_system.api.service;

import lk.maharaja.pos.pos_system.api.dto.CustomerRequestDTO;
import lk.maharaja.pos.pos_system.util.StandardResponse;

public interface CustomerService {
    StandardResponse save(CustomerRequestDTO customerRequestDTO);

    StandardResponse update(CustomerRequestDTO customerRequestDTO, int customerId);

    StandardResponse getAllCustomers();

    StandardResponse getCustomerById(int customerId);

    StandardResponse deleteCustomer(int customerId);
}
