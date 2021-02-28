package lk.maharaja.pos.pos_system.api.controller;


import lk.maharaja.pos.pos_system.api.dto.CustomerRequestDTO;
import lk.maharaja.pos.pos_system.api.dto.ItemRequestDTO;
import lk.maharaja.pos.pos_system.api.service.ItemService;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ItemController {
    @Autowired
    private ItemService itemService;

    //Add New Item
    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResponseEntity<StandardResponse> save(@RequestBody ItemRequestDTO itemDto) {
        try {
            StandardResponse standardResponse = itemService.save(itemDto);
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
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    //Get All Items
    @GetMapping(value = "/getAll")
    public ResponseEntity<StandardResponse> getAllItems() {
        try {
            StandardResponse standardResponse = itemService.getAllItems();
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    //Get All Item By ItemId
    @GetMapping(value = "/getItems/{itemId}")
    public ResponseEntity<StandardResponse> getCustomer(@PathVariable int itemId) {
        try {
            StandardResponse standardResponse = itemService.getItemsByItemId(itemId);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    //Delete Item By ItemId
    @DeleteMapping(value = "/deleteItem/{itemId}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable int itemId) {
        try {
            StandardResponse standardResponse = itemService.deleteItem(itemId);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }
}
