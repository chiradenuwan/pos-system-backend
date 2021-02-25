package lk.maharaja.pos.pos_system.api.dao;

import lk.maharaja.pos.pos_system.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Integer> {
}
