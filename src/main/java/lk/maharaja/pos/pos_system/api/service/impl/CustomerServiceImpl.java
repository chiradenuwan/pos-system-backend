package lk.maharaja.pos.pos_system.api.service.impl;

import lk.maharaja.pos.pos_system.api.dao.CustomerRepository;
import lk.maharaja.pos.pos_system.api.dto.*;
import lk.maharaja.pos.pos_system.api.service.CustomerService;
import lk.maharaja.pos.pos_system.common.alert.Alerts;
import lk.maharaja.pos.pos_system.model.Customer;
import lk.maharaja.pos.pos_system.model.Item;
import lk.maharaja.pos.pos_system.model.OrderData;
import lk.maharaja.pos.pos_system.model.Orders;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
        Customer save = customerRepository.save(customer);
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
        Customer updateCust = customerRepository.save(update);
        if (updateCust != null) {
            return new StandardResponse(200, Alerts.updateSuccess, updateCust);
        } else {
            return new StandardResponse(201, Alerts.updateFailed, null);
        }
    }

    @Override
    public StandardResponse getAllCustomers() {
        List<Customer> all = (List<Customer>) customerRepository.findAll();
        ArrayList<CustomerReponseDTO> customerReponseDTOS = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            List<OrderResponseDTO> orderResponseDTO = null;
            if (all.get(i).getOrders().size() > 0) {
                orderResponseDTO = getOrderDetails(all.get(i).getOrders());
            }

            customerReponseDTOS.add(new CustomerReponseDTO(
                    all.get(i).getId(),
                    all.get(i).getName(),
                    all.get(i).getMobile(),
                    all.get(i).getAddress(),
                    orderResponseDTO
            ));
        }
        return new StandardResponse(200, Alerts.ok, customerReponseDTOS);

    }

    private List<OrderResponseDTO> getOrderDetails(List<Orders> orders) {
        ArrayList<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        ArrayList<OrderDataReponseDTO> orderDataReponseDTOS = new ArrayList<>();
        List<OrderData> orderData = orders.get(0).getOrderData();
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
            orderResponseDTOS.add(new OrderResponseDTO(
                    orders.get(0).getId(),
                    orders.get(0).getDate(),
                    orders.get(0).getTotalAmount(),
                    orders.get(0).getTotalDiscount(),
                    orderDataReponseDTOS
            ));
        }
        return orderResponseDTOS;
    }

    private ItemResponseDTO getItemsInOrderData(Item item) {
        return new ItemResponseDTO(
                item.getId(),
                item.getName(),
                item.getQty(),
                item.getUnit_price()
        );
    }

    @Override
    public StandardResponse getCustomerById(int customerId) {
        Optional<Customer> allById = customerRepository.findById(customerId);
        ArrayList<CustomerReponseDTO> customerReponseDTOS = new ArrayList<>();
        if (allById.isPresent()) {
            List<OrderResponseDTO> orderResponseDTO = null;
            if (allById.get().getOrders().size() > 0) {
                orderResponseDTO = getOrderDetails(allById.get().getOrders());
            }

            customerReponseDTOS.add(new CustomerReponseDTO(
                    allById.get().getId(),
                    allById.get().getName(),
                    allById.get().getMobile(),
                    allById.get().getAddress(),
                    orderResponseDTO
            ));
            return new StandardResponse(200, Alerts.ok, customerReponseDTOS);
        } else {
            return new StandardResponse(200, Alerts.nosuchfound, null);
        }
    }

    @Override
    public StandardResponse deleteCustomer(int customerId) {
        Optional<Customer> byId = customerRepository.findById(customerId);
        if (!byId.isPresent()) {
            return new StandardResponse(201, Alerts.nosuchfound, null);
        } else {
            customerRepository.delete(byId.get());
            return new StandardResponse(200, Alerts.removeSuccess, null);
        }
    }

    @Override
    public StandardResponse getAllCustomerDetails() {
        List<Customer> all = (List<Customer>) customerRepository.findAll();
        ArrayList<CustomerReponseDTO> customerReponseDTOS = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            customerReponseDTOS.add(new CustomerReponseDTO(
                    all.get(i).getId(),
                    all.get(i).getName(),
                    all.get(i).getMobile(),
                    all.get(i).getAddress()
            ));
        }
        return new StandardResponse(200, Alerts.ok, customerReponseDTOS);
    }
}
