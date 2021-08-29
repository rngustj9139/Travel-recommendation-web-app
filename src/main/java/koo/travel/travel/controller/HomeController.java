package koo.travel.travel.controller;

import koo.travel.travel.domain.TarDecoListDomain;
import koo.travel.travel.service.TarDecoListRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final TarDecoListRestService tarDecoListRestService;

    @Autowired
    public HomeController(TarDecoListRestService tarDecoListRestService) {
        this.tarDecoListRestService = tarDecoListRestService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<TarDecoListDomain> members = tarDecoListRestService.findMembers();
        model.addAttribute("members", members);
        return "full-img-cover" ;
    }

    @GetMapping("login")
    public String login() {
        return "Login";
    }

}
