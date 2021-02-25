package lk.maharaja.pos.pos_system.api.service.impl;

import lk.maharaja.pos.pos_system.api.dao.ItemRepository;
import lk.maharaja.pos.pos_system.api.dto.ItemRequestDTO;
import lk.maharaja.pos.pos_system.api.service.ItemService;
import lk.maharaja.pos.pos_system.common.alert.Alerts;
import lk.maharaja.pos.pos_system.model.Customer;
import lk.maharaja.pos.pos_system.model.Item;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Override
    public StandardResponse save(ItemRequestDTO itemDto) {
        Item item = new Item(
                itemDto.getName(),
                itemDto.getQty(),
                itemDto.getUnit_price()
        );
        System.out.println("item : " + item);
        Item save = itemRepository.save(item);
        System.out.println(save);
        if (save != null) {
            return new StandardResponse(200, Alerts.saveSuccess, save);
        } else {
            return new StandardResponse(201, Alerts.saveFailed, null);
        }
    }

    @Override
    public StandardResponse update(ItemRequestDTO itemDto, int itemId) {
        Optional<Item> byId = itemRepository.findById(itemId);
        System.out.println("byId : " + byId);
        if (byId == null) {
            return new StandardResponse(201, Alerts.nosuchfound, null);
        }
        Item item = new Item(
                itemId,
                itemDto.getName(),
                itemDto.getQty(),
                itemDto.getUnit_price()
        );
        System.out.println("item : " + item);
        Item update = itemRepository.save(item);
        System.out.println(update);
        if (update != null) {
            return new StandardResponse(200, Alerts.saveSuccess, update);
        } else {
            return new StandardResponse(201, Alerts.saveFailed, null);
        }
    }

    @Override
    public StandardResponse getAllItems() {
        Iterable<Item> all = itemRepository.findAll();
        return new StandardResponse(200, Alerts.okcustomer, all);
    }

    @Override
    public StandardResponse getItemsByItemId(int itemId) {
        Iterable<Item> allById = itemRepository.findAllById(Collections.singleton(itemId));
        System.out.println(allById.spliterator().estimateSize());
        if (allById.spliterator().estimateSize()!=0){
            return new StandardResponse(200, Alerts.okcustomer, allById);
        }else{
            return new StandardResponse(200, Alerts.nosuchfound, allById);
        }
    }

    @Override
    public StandardResponse deleteItem(int itemId) {
        Optional<Item> byId = itemRepository.findById(itemId);
        System.out.println(byId.isPresent());
        if (!byId.isPresent()) {
            return new StandardResponse(201, Alerts.nosuchfound, null);
        }else{
            itemRepository.deleteById(itemId);
            return new StandardResponse(200, Alerts.removeSuccess, itemRepository.findAll());
        }
    }
}
