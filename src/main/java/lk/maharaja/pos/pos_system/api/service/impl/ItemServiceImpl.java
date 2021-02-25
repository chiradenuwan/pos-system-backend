package lk.maharaja.pos.pos_system.api.service.impl;

import lk.maharaja.pos.pos_system.api.dao.ItemRepository;
import lk.maharaja.pos.pos_system.api.dto.ItemRequestDTO;
import lk.maharaja.pos.pos_system.api.service.ItemService;
import lk.maharaja.pos.pos_system.common.alert.Alerts;
import lk.maharaja.pos.pos_system.model.Item;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
