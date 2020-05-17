package ru.example.securityapp.dto;

import ru.deltasolutions.newsapi.constants.Languages;
import ru.deltasolutions.newsapi.constants.SortBys;

import java.sql.Date;
import java.util.List;

public class EverythingRequestDto {

    private String q;
    private List<String> sources;
    private List<String> domains;
    private String from;
    private String to;
    private Languages language;
    private SortBys sortBy;
    private int pageSize;
    private int page;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public SortBys getSortBy() {
        return sortBy;
    }

    public void setSortBy(SortBys sortBy) {
        this.sortBy = sortBy;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
