package lk.maharaja.pos.pos_system.api.controller;

import lk.maharaja.pos.pos_system.api.dto.OrderRequestDTO;
import lk.maharaja.pos.pos_system.api.service.OrderService;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //Add New Order
    @PostMapping(value = "/save", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<StandardResponse> save(@RequestBody OrderRequestDTO orderRequestDTO) {
        try {
            StandardResponse standardResponse = orderService.save(orderRequestDTO);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    //Get All Orders
    @GetMapping(value = "/getAll")
    public ResponseEntity<StandardResponse> getCustomer() {
        try {
            StandardResponse standardResponse = orderService.getAllOrders();
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    //Get All Orders By OrderId
    @GetMapping(value = "/getOrders/{orderId}")
    public ResponseEntity<StandardResponse> getOrders(@PathVariable int orderId) {
        try {
            StandardResponse standardResponse = orderService.getOrderByOrderId(orderId);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }
}
