package koo.travel.travel.repository;

import koo.travel.travel.domain.TarDecoListDomain;
import org.springframework.stereotype.Repository;

public interface TarDecoListRepository {
    TarDecoListDomain save(TarDecoListDomain tarDecoListDomain);
}
