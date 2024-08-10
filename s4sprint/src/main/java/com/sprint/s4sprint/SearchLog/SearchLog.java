package com.sprint.s4sprint.SearchLog;

import com.sprint.s4sprint.User.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class SearchLog {
    @Id
    @SequenceGenerator(name = "search_log_sequence", sequenceName = "search_log_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "search_log_sequence")
    private long searchLogId;
    private Date searchDateTime;
    private String searchTerms;
    @OneToOne
    private User user;

    public long getSearchLogId() {
        return searchLogId;
    }

    public void setSearchLogId(long searchLogId) {
        this.searchLogId = searchLogId;
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
