package com.example.movietmdb.model;

import java.util.List;
public class popularMoviesResponse {

    private Integer page;
    private List<popularMoviesResults> results;
    private Integer totalPages;
    private Integer totalResults;

    public popularMoviesResponse(Integer page, List<popularMoviesResults> results, Integer totalPages, Integer totalResults) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<popularMoviesResults> getResults() {
        return results;
    }

    public void setResults(List<popularMoviesResults> results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
