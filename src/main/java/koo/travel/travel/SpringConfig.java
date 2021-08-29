package koo.travel.travel;

import koo.travel.travel.repository.HomeRepository;
import koo.travel.travel.repository.JpaHomeRepository;
import koo.travel.travel.repository.JpaTarDecoListRepository;
import koo.travel.travel.repository.TarDecoListRepository;
import koo.travel.travel.service.HomeService;
import koo.travel.travel.service.TarDecoListRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public TarDecoListRestService tarDecoListRestService() {
        return new TarDecoListRestService(tarDecoListRepository());
    }

    @Bean
    public TarDecoListRepository tarDecoListRepository() {
        return new JpaTarDecoListRepository(em);
    }

    @Bean
    public HomeService homeService() {
        return new HomeService(homeRepository());
    }

    @Bean
    public HomeRepository homeRepository() {
        return new JpaHomeRepository(em);
    }

}
