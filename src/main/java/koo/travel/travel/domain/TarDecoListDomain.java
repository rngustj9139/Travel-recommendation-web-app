package koo.travel.travel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TarDecoListDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int estiDecoDivCd;
    private Long contentId;
    private Long baseYmd;
    private double cncrtAccPsonNum;
    private double estiNum;

    public static class Builder { // 점층적 생성자 패턴과 자바 빈즈 패턴의 모든 장점을 이용 할 수 있는 빌더 패턴 이용
        private int id;
        private String title;
        private int estiDecoDivCd;
        private Long contentId;
        private Long baseYmd;
        private double cncrtAccPsonNum;
        private double estiNum;

        public Builder(String title, int estiDecoDivCd, Long contentId) { // 필수적으로 받아야 되는 정보
            this.title = title;
            this.estiDecoDivCd = estiDecoDivCd;
            this.contentId = contentId;
        }

        public Builder setBaseYmd(Long baseYmd) { // 선택적으로 받아도 되는 정보
            this.baseYmd = baseYmd;
            return this;
        }

        public Builder setCncrtAccPsonNum(double cncrtAccPsonNum) { // 선택적으로 받아도 되는 정보
            this.cncrtAccPsonNum = cncrtAccPsonNum;
            return this;
        }

        public Builder setEstiNum(double estiNum) { // 선택적으로 받아도 되는 정보
            this.estiNum = estiNum;
            return this;
        }

        public TarDecoListDomain build() {
            TarDecoListDomain tarDecoListDomain = new TarDecoListDomain();
            tarDecoListDomain.title = title;
            tarDecoListDomain.estiDecoDivCd = estiDecoDivCd;
            tarDecoListDomain.contentId = contentId;
            tarDecoListDomain.baseYmd = baseYmd;
            tarDecoListDomain.cncrtAccPsonNum = cncrtAccPsonNum;
            tarDecoListDomain.estiNum = estiNum;
            return tarDecoListDomain;
        }

    }
}



