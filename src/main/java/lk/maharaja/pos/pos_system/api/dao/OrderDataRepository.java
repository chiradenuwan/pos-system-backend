package lk.maharaja.pos.pos_system.api.dao;

import lk.maharaja.pos.pos_system.model.OrderData;
import org.springframework.data.repository.CrudRepository;

public interface OrderDataRepository extends CrudRepository<OrderData,Integer> {

}
