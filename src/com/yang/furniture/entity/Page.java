package com.yang.furniture.entity;

import java.util.List;

/**
 * @author 刘洋
 * @date 2022/5/29  10:24 AM
 */
public class Page<T> {

    public static final Integer PAGE_SIZE = 3;
    private Integer pageNo;
    private List<T> items;

    private Integer pageTotalCount;

    private Integer totalRow;

    private String url;

    private Integer pageSize = PAGE_SIZE;
    public Page() {
    }

    public Page(Integer pagerNumber, List<T> items, Integer pageTotalCount, Integer totalRow, String url) {
        this.pageNo = pagerNumber;
        this.items = items;
        this.pageTotalCount = pageTotalCount;
        this.totalRow = totalRow;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPagerNumber(Integer pagerNumber) {
        this.pageNo = pagerNumber;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", items=" + items +
                ", pageTotalCount=" + pageTotalCount +
                ", totalRow=" + totalRow +
                ", url='" + url + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}
