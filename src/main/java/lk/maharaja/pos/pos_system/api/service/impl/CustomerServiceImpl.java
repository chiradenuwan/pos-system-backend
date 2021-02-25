package lk.maharaja.pos.pos_system.api.service.impl;

import lk.maharaja.pos.pos_system.api.dao.CustomerRepository;
import lk.maharaja.pos.pos_system.api.dto.CustomerRequestDTO;
import lk.maharaja.pos.pos_system.api.service.CustomerService;
import lk.maharaja.pos.pos_system.common.alert.Alerts;
import lk.maharaja.pos.pos_system.model.Customer;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public StandardResponse save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = new Customer(
                customerRequestDTO.getName(),
                customerRequestDTO.getMobile(),
                customerRequestDTO.getAddress()
        );
        System.out.println("customer : " + customer);
        Customer save = customerRepository.save(customer);
        System.out.println(save);
        if (save != null) {
            return new StandardResponse(200, Alerts.registerSuccess, save);
        } else {
            return new StandardResponse(201, Alerts.registerFailed, null);
        }
    }
}
