package lk.maharaja.pos.pos_system.api.service.impl;

import lk.maharaja.pos.pos_system.api.dao.UserRepository;
import lk.maharaja.pos.pos_system.api.service.UserService;
import lk.maharaja.pos.pos_system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
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
}
