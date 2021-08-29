package koo.travel.travel.repository;

import koo.travel.travel.domain.TarDecoListDomain;

import java.util.List;

public interface TarDecoListRepository {
    TarDecoListDomain save(TarDecoListDomain tarDecoListDomain);
    List<TarDecoListDomain> findAll();
}



