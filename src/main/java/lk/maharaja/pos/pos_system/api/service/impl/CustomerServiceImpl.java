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
        List<Customer> all = (List<Customer>) customerRepository.findAll();
        System.out.println(all);
        ArrayList<CustomerReponseDTO> customerReponseDTOS = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            System.out.println("i : " + all.get(i));
            List<OrderResponseDTO> orderResponseDTO = null;
            if (all.get(i).getOrders().size() > 0) {
                orderResponseDTO = getOrderDetails(all.get(i).getOrders());
            }

            System.out.println("orderResponseDTO : " + orderResponseDTO);
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
        System.out.println("Orders : " + orders);
        ArrayList<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        ArrayList<OrderDataReponseDTO> orderDataReponseDTOS = new ArrayList<>();
        List<OrderData> orderData = orders.get(0).getOrderData();
        for (int i = 0; i < orderData.size(); i++) {
            System.out.println("Order data obj : " + orderData.get(i));
            ItemResponseDTO itemResponseDTO = getItemsInOrderData(orderData.get(i).getItem());
            System.out.println("itemResponseDTO data obj : " + itemResponseDTO);
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
        System.out.println("item ============> : " + item);
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
        System.out.println("allByIdById : " + allById);
        ArrayList<CustomerReponseDTO> customerReponseDTOS = new ArrayList<>();

        System.out.println("i : " + allById);


        System.out.println("allByIdById : " + allById);
        System.out.println(allById.isPresent());
        if (allById.isPresent()) {
            List<OrderResponseDTO> orderResponseDTO = null;
            if (allById.get().getOrders().size() > 0) {
                orderResponseDTO = getOrderDetails(allById.get().getOrders());
            }

            System.out.println("orderResponseDTO : " + orderResponseDTO);
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
        System.out.println(byId.isPresent());
        if (!byId.isPresent()) {
            return new StandardResponse(201, Alerts.nosuchfound, null);
        } else {
            customerRepository.deleteById(customerId);
            return new StandardResponse(200, Alerts.removeSuccess, customerRepository.findAll());
        }
    }
}
