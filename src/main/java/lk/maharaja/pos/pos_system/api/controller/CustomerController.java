package lk.maharaja.pos.pos_system.api.controller;

import lk.maharaja.pos.pos_system.api.dto.CustomerRequestDTO;
import lk.maharaja.pos.pos_system.api.service.CustomerService;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Add New Customer
    @PostMapping(value = "/save", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<StandardResponse> save(@RequestBody CustomerRequestDTO customerRequestDTO) {
        try {
            StandardResponse standardResponse = customerService.save(customerRequestDTO);
            System.out.println(standardResponse);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    // Update Customer
    @PutMapping(value = "/update/{customerId}", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<StandardResponse> save(@RequestBody CustomerRequestDTO customerRequestDTO, @PathVariable int customerId) {
        try {
            StandardResponse standardResponse = customerService.update(customerRequestDTO, customerId);
            System.out.println(standardResponse);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    //Get All Customer
    @GetMapping(value = "/getAll")
    public ResponseEntity<StandardResponse> getCustomer() {
        try {
            StandardResponse standardResponse = customerService.getAllCustomers();
            System.out.println(standardResponse);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    //Get All Customer By CustomerId
    @GetMapping(value = "/getCustomer/{customerId}")
    public ResponseEntity<StandardResponse> getCustomer(@PathVariable int customerId) {
         try {
            StandardResponse standardResponse = customerService.getCustomerById(customerId);
            System.out.println("standardResponse : "+standardResponse);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    //Delete Customer By CustomerId
    @DeleteMapping(value = "/deleteCustomer/{customerId}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable int customerId) {
        try {
            StandardResponse standardResponse = customerService.deleteCustomer(customerId);
            System.out.println(standardResponse);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }
}
