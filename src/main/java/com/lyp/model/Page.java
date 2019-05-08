package com.lyp.model;

import java.io.Serializable;

public class Page implements Serializable{
    private static final long serialVersionUID = -3198048449643774660L;

    private int pageNow = 1;

    private int pageSize = 5;

    private int totalCount;

    private int totalPageCount;

    @SuppressWarnings("unuse")
    private int startPos;

    @SuppressWarnings("unuse")
    private  boolean hasFirst;

    @SuppressWarnings("unuse")
    private boolean hasPre;

    @SuppressWarnings("unuse")
    private boolean hasNext;

    @SuppressWarnings("unuse")
    private boolean hasLast;

    public Page(int totalCount,int pageNow){
        this.totalCount = totalCount;
        this.pageNow = pageNow;
    }

    public int getTotalPageCount() {
        totalPageCount = getTotalCount()/getPageSize();
        return (totalCount % pageSize == 0) ? totalPageCount
                : totalPageCount + 1;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartPos() {
        return (pageNow - 1) * pageSize;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public boolean isHasFirst() {
        return (pageNow == 1) ? false : true;
    }

    public void setHasFirst(boolean hasFirst) {
        this.hasFirst = hasFirst;
    }

    public boolean isHasPre() {
        // 如果有首页就有前一页，因为有首页就不是第一页
        return isHasFirst() ? true : false;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }

    public boolean isHasNext() {
        // 如果有尾页就有下一页，因为有尾页表明不是最后一页
        return isHasLast() ? true : false;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasLast() {
        // 如果不是最后一页就有尾页
        return (pageNow == getTotalCount()) ? false : true;
    }

    public void setHasLast(boolean hasLast) {
        this.hasLast = hasLast;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNow=" + pageNow +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPageCount=" + totalPageCount +
                ", startPos=" + startPos +
                ", hasFirst=" + hasFirst +
                ", hasPre=" + hasPre +
                ", hasNext=" + hasNext +
                ", hasLast=" + hasLast +
                '}';
    }
}
