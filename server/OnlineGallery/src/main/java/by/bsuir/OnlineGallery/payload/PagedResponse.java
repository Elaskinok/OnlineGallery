package by.bsuir.OnlineGallery.payload;

import java.util.List;

public class PagedResponse<T> {

    private final List<T> contentList;
    private final Integer page;
    private final Long totalElements;
    private final Integer totalPages;
    private final Boolean last;

    public PagedResponse(List<T> contentList, int page, long totalElements, int totalPages, boolean last) {
        this.contentList = contentList;
        this.page = page;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    public List<T> getContentList() {
        return contentList;
    }

    public int getPage() {
        return page;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isLast() {
        return last;
    }
}
