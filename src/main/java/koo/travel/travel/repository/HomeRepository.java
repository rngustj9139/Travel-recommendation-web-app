package koo.travel.travel.repository;

import koo.travel.travel.domain.TarDecoListDomain;
import koo.travel.travel.paging.PagingCriteria;

import java.util.List;
import java.util.Map;

public interface HomeRepository {
    List<Map<Long, TarDecoListDomain>> findAll(PagingCriteria pagingCriteria);
}
