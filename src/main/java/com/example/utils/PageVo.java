package com.example.utils;

import java.util.List;

public class PageVo<T> {

    private int currentPage;

    private int pageSize;

    private long totalRecords;

    private long totalPages;

    private List<T> records;

    public PageVo(int currentPage, int pageSize, long totalRecords, List<T> records) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRecords = totalRecords;
        this.totalPages = (long) Math.ceil((double) totalRecords / pageSize);
        this.records = records;
    }

}
