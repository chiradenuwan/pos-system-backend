package lk.maharaja.pos.pos_system.api.dao;

import lk.maharaja.pos.pos_system.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User getUserByUsername(String username);

    int countUserByUsername(String username);
}
