package lk.maharaja.pos.pos_system.filter;

import lk.maharaja.pos.pos_system.api.dao.UserRepository;
import lk.maharaja.pos.pos_system.model.User;
import lk.maharaja.pos.pos_system.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = httpServletRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")) {

            token = token.substring(7);

            String username = jwtTokenUtil.getUsernameFromToken(token);
            User user = null;
            try {
                user = userRepository.getUserByUsername(username);
            } catch (Exception e) {
                e.printStackTrace();
            }

            httpServletResponse.setHeader("token", jwtTokenUtil.generateToken(user));
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);

    }

}