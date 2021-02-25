package lk.maharaja.pos.pos_system.api.service;

import lk.maharaja.pos.pos_system.api.dto.UserDto;
import lk.maharaja.pos.pos_system.model.User;
import lk.maharaja.pos.pos_system.util.StandardResponse;

public interface UserService {
    User loadUsernameAndPassword(String username);

    StandardResponse save(UserDto userDto);

}
