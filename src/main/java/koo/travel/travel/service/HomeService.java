package koo.travel.travel.service;

import koo.travel.travel.domain.TarDecoListDomain;
import koo.travel.travel.paging.PagingCriteria;
import koo.travel.travel.repository.HomeRepository;

import java.util.List;
import java.util.Map;

public class HomeService {
    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public List<Map<Long, TarDecoListDomain>> findMembers(PagingCriteria pagingCriteria) {
        return homeRepository.findAll(pagingCriteria);
    }

}
