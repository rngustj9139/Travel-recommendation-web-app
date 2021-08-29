package koo.travel.travel.paging;

public class PageMaker { // 여행 리스트 페이징을 처리하는 PageMaker 클래스 (페이징에 관련된 버튼들을 만드는 기능을 하는 클래스이다, PageMaker 객체를 사용하려면 setCri() 와 setTotalCount()를 먼저 호출해서 값을 셋팅해야 한다, 또한 PagingCriteria 객체에서 필요한 page 와 perPageNum을 사용하기 위해서 setCri()를 먼저 셋팅해야 한다.

    private PagingCriteria cri;
    private int totalCount; // 총 여행지 수
    private int startPage; // 현 페이지 위치에서 표시되는 제일 왼쪽의 페이지 버튼 번호(상대적), 시작 페이지 번호 = (끝 페이지 번호 - 화면에 보여질 페이지 번호의 갯수) + 1
    private int endPage; // 현 페이지 위치에서 표시되는 제일 오른쪽의 페이지 버튼 번호(상대적), 끝 페이지 번호 = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 갯수) * 화면에 보여질 페이지 번호의 갯수
    private boolean prev; // 이전 버튼 생성 유무
    private boolean next; // 다음 버튼 생성 유무
    private int displayPageNum = 5; // 하단에 표시될 버튼의 수

    public PagingCriteria getCri() {
        return cri;
    }
    public void setCri(PagingCriteria cri) {
        this.cri = cri;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) { // 총 여행지 수를 먼저 구한 후 calcData() 수행
        this.totalCount = totalCount;
        calcData();
    }

    private void calcData() { // 페이징 관련 버튼 계산 수행

        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;

        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum())); // tempEndPage: 절대적인 마지막 페이지 버튼 번호(절대적)
        if (endPage > tempEndPage) { // 만약 100개의 게시글을 20개씩 보여준다고 하면 끝 페이지의 번호가 5여야 하는데 위의 계산식으로 계산을 하게 되면 끝 페이지 번호가 20이 나오게 된다. ( Math.ceil(1/20)*20 = 20 ) 그래서 총 게시글 수와 페이지당 보여줄 게시글의 갯수로 마지막 페이지 번호를 구해 서로 비교해서 화면에 보여질 끝 페이지 번호를 구해야 한다.
            endPage = tempEndPage;
        }

        prev = startPage == 1 ? false : true;
        next = endPage * cri.getPerPageNum() < totalCount ? true : false;

    }

    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }
    public int getDisplayPageNum() {
        return displayPageNum;
    }
    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }

}
