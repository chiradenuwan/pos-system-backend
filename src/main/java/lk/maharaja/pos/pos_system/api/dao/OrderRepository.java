package lk.maharaja.pos.pos_system.api.dao;

import lk.maharaja.pos.pos_system.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders,Integer> {
}
