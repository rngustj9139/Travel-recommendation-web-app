package koo.travel.travel.controller;

import koo.travel.travel.domain.TarDecoListDomain;
import koo.travel.travel.paging.PageMaker;
import koo.travel.travel.paging.PagingCriteria;
import koo.travel.travel.service.HomeService;
import koo.travel.travel.service.TarDecoListRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String home(Model model, PagingCriteria pagingCriteria) throws Exception{
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(pagingCriteria);
        pageMaker.setTotalCount(100); // 추가예정: 여행지 개수 가져오기 기능

        List<Map<Long, TarDecoListDomain>> members = homeService.findMembers(pagingCriteria); // 파라미터 재확인하기
        model.addAttribute("list", members);
        model.addAttribute("pageMaker", pageMaker);

        return "full-img-cover" ;
    }


    @GetMapping("login")
    public String login() {
        return "Login";
    }

}
