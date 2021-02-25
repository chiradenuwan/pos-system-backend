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

}
