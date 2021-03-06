package koo.travel.travel.service;

import koo.travel.travel.domain.TarDecoListDomain;
import koo.travel.travel.paging.PagingCriteria;
import koo.travel.travel.repository.TarDecoListRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TarDecoListRestService {

    private final TarDecoListRepository tarDecoListRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 10; // 블럭에 존재하는 페이지 수
    private static final int PAGE_POST_COUNT = 10; // 한 페이지에 존재하는 게시글 수

    public TarDecoListRestService(TarDecoListRepository tarDecoListRepository) {
        this.tarDecoListRepository = tarDecoListRepository;
    }

    public void init(String jsonData) {
        /**
         * JSON parser를 통해 긴 문자열 형식으로 들어온 JSON 데이터를 객체(object)로 변환 시켜야함
        **/
        try {
            JSONObject jObj; // JSON 객체 생성
            JSONParser jsonParser = new JSONParser(); // JSON 파싱 객체 생성

            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonData); // 파싱할 String (RestController에서 호출해 값이 저장된 StringBuilder sb)을 JSON 객체로 파서를 통해 저장

            //데이터 분해 단계
            JSONObject parseResponse = (JSONObject) jsonObj.get("response"); // response를 받아옴, parseResponse에는 response 내부의 데이터들이 들어있음
            JSONObject parseBody = (JSONObject) parseResponse.get("body"); // body를 받아옴, parseBody에는 body 내부의 데이터들이 들어있음
            JSONObject parseItems = (JSONObject) parseBody.get("items");
            JSONArray array = (JSONArray) parseItems.get("item"); // items 안쪽의 데이터는 [], 즉 배열의 형태이기에 JSON 배열로 받아온다.

            for(int i = 0; i < array.size(); i++) {
                jObj = (JSONObject) array.get(i);

                TarDecoListDomain tarDecoListDomain = new TarDecoListDomain.Builder(jObj.get("title").toString(), Integer.parseInt(jObj.get("estiDecoDivCd").toString()), Long.valueOf(String.valueOf(jObj.get("contentId")))) // 도메인 클래스 빌더패턴으로 값 삽입하기
                        .setBaseYmd(Long.valueOf(String.valueOf(jObj.get("baseYmd")))) // object type인 jObj.get("baseYmd")을 Long type으로 형변환
                        .setCncrtAccPsonNum(Double.parseDouble(jObj.get("cncrtAccPsonNum").toString())) // object type인 jObj.get("cncrtAccPsonNum")을 int type으로 형변환
                        .setEstiNum(Double.parseDouble(jObj.get("estiNum").toString())) // object type인 jObj.get("estiNum")을 int type으로 형변환
                        .build();

                tarDecoListRepository.save(tarDecoListDomain);// JPA로 H2에 객체 삽입 (domain 삽입)
                //TarDecoListDomain result = tarDecoListRepository.save(tarDecoListDomain);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}


