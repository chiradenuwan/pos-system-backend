package lk.maharaja.pos.pos_system.api.controller;

import lk.maharaja.pos.pos_system.util.StandardResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/signIn")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    @GetMapping("/login")
    public String isadded(){
        return "Ok";
    }

    @PostMapping("/register")
    public StandardResponse register(){
        return new StandardResponse(200,"",null);
    }

}
