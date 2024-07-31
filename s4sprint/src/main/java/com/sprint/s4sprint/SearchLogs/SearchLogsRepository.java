package com.sprint.s4sprint.SearchLogs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchLogsRepository extends CrudRepository<SearchLogs, Long>  {
    public SearchLogs findSearchLoggingsBySearchTerms(String searchTerms);
}

