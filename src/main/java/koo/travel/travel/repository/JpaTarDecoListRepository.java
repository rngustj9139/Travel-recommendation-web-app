package koo.travel.travel.repository;

import koo.travel.travel.domain.TarDecoListDomain;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

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
