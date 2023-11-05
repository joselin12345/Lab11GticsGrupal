package edu.pucp.gtics.lab11_gtics_20232.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {
    @GetMapping("/openLoginWindow")
    public String loginWindow(){
        return "/user/loginWindow";
    }
}
