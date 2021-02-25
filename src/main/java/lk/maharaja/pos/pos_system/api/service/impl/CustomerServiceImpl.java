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
import java.util.Collections;
import java.util.Optional;

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
            return new StandardResponse(200, Alerts.saveSuccess, save);
        } else {
            return new StandardResponse(201, Alerts.saveFailed, null);
        }
    }

    @Override
    public StandardResponse update(CustomerRequestDTO customerRequestDTO, int customerId) {
        Optional<Customer> byId = customerRepository.findById(customerId);
        if (byId == null) {
            return new StandardResponse(201, Alerts.nosuchfound, null);
        }
        Customer update = new Customer(
                customerId,
                customerRequestDTO.getName(),
                customerRequestDTO.getMobile(),
                customerRequestDTO.getAddress()
        );
        System.out.println("customer update: " + update);
        Customer updateCust = customerRepository.save(update);
        System.out.println(updateCust);
        if (updateCust != null) {
            return new StandardResponse(200, Alerts.updateSuccess, updateCust);
        } else {
            return new StandardResponse(201, Alerts.updateFailed, null);
        }
    }

    @Override
    public StandardResponse getAllCustomers() {
        Iterable<Customer> all = customerRepository.findAll();
        return new StandardResponse(200, Alerts.okcustomer, all);
    }

    @Override
    public StandardResponse getCustomerById(int customerId) {
        Iterable<Customer> allById = customerRepository.findAllById(Collections.singleton(customerId));
        System.out.println(allById.spliterator().estimateSize());
        if (allById.spliterator().estimateSize()!=0){
            return new StandardResponse(200, Alerts.okcustomer, allById);
        }else{
            return new StandardResponse(200, Alerts.nosuchfound, allById);
        }

    }

    @Override
    public StandardResponse deleteCustomer(int customerId) {
        Optional<Customer> byId = customerRepository.findById(customerId);
        System.out.println(byId.isPresent());
        if (!byId.isPresent()) {
            return new StandardResponse(201, Alerts.nosuchfound, null);
        }else{
            customerRepository.deleteById(customerId);
            return new StandardResponse(200, Alerts.removeSuccess, customerRepository.findAll());
        }
    }
}
