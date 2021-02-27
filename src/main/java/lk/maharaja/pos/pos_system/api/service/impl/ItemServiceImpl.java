package lk.maharaja.pos.pos_system.api.service.impl;

import lk.maharaja.pos.pos_system.api.dao.ItemRepository;
import lk.maharaja.pos.pos_system.api.dto.ItemRequestDTO;
import lk.maharaja.pos.pos_system.api.dto.ItemResponseDTO;
import lk.maharaja.pos.pos_system.api.service.ItemService;
import lk.maharaja.pos.pos_system.common.alert.Alerts;
import lk.maharaja.pos.pos_system.model.Item;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
            return new StandardResponse(200, Alerts.updateSuccess, update);
        } else {
            return new StandardResponse(201, Alerts.updateFailed, null);
        }
    }

    @Override
    public StandardResponse getAllItems() {
        List<Item> all = (List<Item>) itemRepository.findAll();
        ArrayList<ItemResponseDTO> itemResponseDTOS = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            itemResponseDTOS.add(new ItemResponseDTO(
                    all.get(i).getId(),
                    all.get(i).getName(),
                    all.get(i).getQty(),
                    all.get(i).getUnit_price()
            ));
        }
        return new StandardResponse(200, Alerts.ok, itemResponseDTOS);
    }

    @Override
    public StandardResponse getItemsByItemId(int itemId) {
        Optional<Item> allById = itemRepository.findById(itemId);

        System.out.println(allById.isPresent());
        if (allById.isPresent()) {
            ItemResponseDTO itemResponseDTO = new ItemResponseDTO(
                    allById.get().getId(),
                    allById.get().getName(),
                    allById.get().getQty(),
                    allById.get().getUnit_price());
            return new StandardResponse(200, Alerts.ok, itemResponseDTO);
        } else {
            return new StandardResponse(200, Alerts.nosuchfound, null);
        }
    }

    @Override
    public StandardResponse deleteItem(int itemId) {
        Optional<Item> byId = itemRepository.findById(itemId);
        System.out.println("byId : " + byId);
        System.out.println("byId : " + byId.get());
        System.out.println(byId.isPresent());
        if (!byId.isPresent()) {
            return new StandardResponse(201, Alerts.nosuchfound, null);
        } else {
            itemRepository.delete(byId.get());
            return new StandardResponse(200, Alerts.removeSuccess, null);
        }
    }
}
