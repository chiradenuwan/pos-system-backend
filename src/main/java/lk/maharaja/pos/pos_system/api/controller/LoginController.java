package lk.maharaja.pos.pos_system.api.controller;

import lk.maharaja.pos.pos_system.api.dto.UserDto;
import lk.maharaja.pos.pos_system.api.service.UserService;
import lk.maharaja.pos.pos_system.model.User;
import lk.maharaja.pos.pos_system.util.JwtTokenUtil;
import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/signIn")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/login", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<StandardResponse> login(@RequestBody UserDto userDto) {
        try {
            authenticate(userDto.getUsername(), userDto.getPassword());
            User user = userService.loadUsernameAndPassword(userDto.getUsername());
            String token = jwtTokenUtil.generateToken(user);
            StandardResponse standardResponse = new StandardResponse(200, token, null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(401, "INVALID_CREDENTIALS", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/register", consumes = {"application/json"}, produces = "application/json")
    public ResponseEntity<StandardResponse> register(@RequestBody UserDto userDto) {
        try {
            StandardResponse standardResponse = userService.save(userDto);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        } catch (Exception e) {
            StandardResponse standardResponse = new StandardResponse(500, "SERVER_ERROR", null);
            return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
        }
    }

    private StandardResponse authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            return new StandardResponse(401, "INVALID_CREDENTIALS", null);
        }
        return null;
    }

}
