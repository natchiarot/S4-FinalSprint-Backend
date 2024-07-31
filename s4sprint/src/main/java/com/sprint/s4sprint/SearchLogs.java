package com.sprint.s4sprint;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class SearchLogs {
    @Id
    @SequenceGenerator(name = "search_logs_sequence", sequenceName = "search_logs_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "search_logs_sequence")
    private long searchLogsId;
    private LocalDateTime searchDateTime;
    private String searchTerms;
    private List<User> userId;

    public long getSearchLogsId() {
        return searchLogsId;
    }

    public void setSearchLogsId(long searchLogsId) {
        this.searchLogsId = searchLogsId;
    }

    public LocalDateTime getSearchDateTime() {
        return searchDateTime;
    }

    public void setSearchDateTime(LocalDateTime searchDateTime) {
        this.searchDateTime = searchDateTime;
    }

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public List<User> getUserId() {
        return userId;
    }

    public void setUserId(List<User> userId) {
        this.userId = userId;
    }
}
