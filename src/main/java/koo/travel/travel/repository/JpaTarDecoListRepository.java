package koo.travel.travel.repository;

import koo.travel.travel.domain.TarDecoListDomain;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaTarDecoListRepository implements TarDecoListRepository {

    private final EntityManager em;

    public JpaTarDecoListRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public TarDecoListDomain save(TarDecoListDomain tarDecoListDomain) {
        em.persist(tarDecoListDomain);
        return tarDecoListDomain;
    }

}


