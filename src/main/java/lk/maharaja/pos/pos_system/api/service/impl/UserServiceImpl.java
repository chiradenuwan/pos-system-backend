package lk.maharaja.pos.pos_system.api.service.impl;

import lk.maharaja.pos.pos_system.api.dao.UserRepository;
import lk.maharaja.pos.pos_system.api.dto.UserDto;
import lk.maharaja.pos.pos_system.api.service.UserService;
import lk.maharaja.pos.pos_system.common.alert.Alerts;
import lk.maharaja.pos.pos_system.model.User;
import lk.maharaja.pos.pos_system.util.JwtTokenUtil;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        User apiUser = userRepository.getUserByUsername(username);

        if (apiUser != null) {
            return new org.springframework.security.core.userdetails.User(apiUser.getUsername(),
                    apiUser.getPassword(), Collections.singleton(authority));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public User loadUsernameAndPassword(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public StandardResponse save(UserDto userDto) {
        User user = new User(
                userDto.getName(), userDto.getUsername(),passwordEncoder.encode( userDto.getPassword())
        );
        int user1 = userRepository.countUserByUsername(userDto.getUsername());
        if (user1 == 0) {
            User save = userRepository.save(user);
            if (save != null) {
                return new StandardResponse(200, Alerts.registerSuccess, save);
            } else {
                return new StandardResponse(201, Alerts.registerFailed, null);
            }
        } else {
            return new StandardResponse(201, Alerts.usernameInUse, null);
        }


    }
}
