package koo.travel.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "full-img-cover" ;
    }

    @GetMapping("login")
    public String login() {
        return "Login";
    }

}
