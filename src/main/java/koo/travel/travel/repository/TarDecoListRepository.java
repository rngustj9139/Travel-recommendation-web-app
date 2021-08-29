package koo.travel.travel.repository;

import koo.travel.travel.domain.TarDecoListDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TarDecoListRepository {
    TarDecoListDomain save(TarDecoListDomain tarDecoListDomain);
}



