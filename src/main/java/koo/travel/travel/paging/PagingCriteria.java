package koo.travel.travel.paging;

public class PagingCriteria { // 특정 페이지 조회를 위한 PagingCriteria 클래스

    private int page; // 현재 페이지 번호
    private int perPageNum; // 한 페이지당 보여줄 게시글의 개수

    public int getPageStart() { // pageStart:  특정 페이지의 첫번째 게시글의 행 번호(현재 페이지의 게시글 시작 번호 = (현재 페이지 번호 - 1) * 페이지당 보여줄 게시글 갯수)
        return (this.page-1)*perPageNum;
    }

    public PagingCriteria() {
        this.page = 1;
        this.perPageNum = 9;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }
    public int getPerPageNum() {
        return perPageNum;
    }
    public void setPerPageNum(int pageCount) {
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }

}
