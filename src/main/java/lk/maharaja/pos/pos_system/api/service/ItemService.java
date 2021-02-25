package lk.maharaja.pos.pos_system.api.service;

import lk.maharaja.pos.pos_system.api.dto.ItemRequestDTO;
import lk.maharaja.pos.pos_system.util.StandardResponse;

public interface ItemService {
    StandardResponse save(ItemRequestDTO itemDto);

    StandardResponse update(ItemRequestDTO itemDto, int itemId);

    StandardResponse getAllItems();

    StandardResponse getItemsByItemId(int itemId);

    StandardResponse deleteItem(int itemId);
}
