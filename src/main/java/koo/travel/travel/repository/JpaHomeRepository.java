package koo.travel.travel.repository;

import koo.travel.travel.domain.TarDecoListDomain;
import koo.travel.travel.paging.PagingCriteria;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

public class JpaHomeRepository implements HomeRepository {

    private final EntityManager em;

    public JpaHomeRepository(EntityManager em) {
        this.em = em;
    }

    protected Log log = LogFactory.getLog(JpaHomeRepository.class);

    @Override
    @SuppressWarnings("unchecked") // 컴파일 경고 사용 x
    public List<Map<Long, TarDecoListDomain>> findAll(PagingCriteria pagingCriteria) {
        return (List<Map<Long, TarDecoListDomain>>) selectList("board.selectBoardList", pagingCriteria); // selectList 메서드의 인자는 쿼리 이름, 쿼리가 실행되는데 필요한 변수로 2가지이다.
    }

}
