package lk.maharaja.pos.pos_system.api.dao;

import lk.maharaja.pos.pos_system.model.Customer;
 import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}
