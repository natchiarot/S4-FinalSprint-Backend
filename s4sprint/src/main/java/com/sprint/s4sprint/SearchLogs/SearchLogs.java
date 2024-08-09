package com.sprint.s4sprint.SearchLogs;

import com.sprint.s4sprint.User.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class SearchLogs {
    @Id
    @SequenceGenerator(name = "search_logs_sequence", sequenceName = "search_logs_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "search_logs_sequence")
    private long searchLogsId;
    private Date searchDateTime;
    private String searchTerms;
    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;

    public long getSearchLogsId() {
        return searchLogsId;
    }

    public void setSearchLogsId(long searchLogsId) {
        this.searchLogsId = searchLogsId;
    }

    public Date getSearchDateTime() {
        return searchDateTime;
    }

    public void setSearchDateTime(Date searchDateTime) {
        this.searchDateTime = searchDateTime;
    }

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
