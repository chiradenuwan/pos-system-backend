package lk.maharaja.pos.pos_system.api.controller;


import lk.maharaja.pos.pos_system.api.dto.CustomerRequestDTO;
import lk.maharaja.pos.pos_system.api.dto.ItemRequestDTO;
import lk.maharaja.pos.pos_system.api.service.ItemService;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {
    @Autowired
    private ItemService itemService;

    //Add New Item
    @PostMapping(value = "/save", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<StandardResponse> save(@RequestBody ItemRequestDTO itemDto) {
        try {
            StandardResponse standardResponse = itemService.save(itemDto);
            System.out.println(standardResponse);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    //Update Item byId
    @PutMapping(value = "/update/{itemId}", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<StandardResponse> update(@RequestBody ItemRequestDTO itemDto,@PathVariable int itemId) {
        try {
            StandardResponse standardResponse = itemService.update(itemDto,itemId);
            System.out.println(standardResponse);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }
}
