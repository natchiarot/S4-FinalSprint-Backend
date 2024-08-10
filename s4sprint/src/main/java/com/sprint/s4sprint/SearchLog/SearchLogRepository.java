package com.sprint.s4sprint.SearchLog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchLogRepository extends CrudRepository<SearchLog, Long>  {
    public List<SearchLog> findSearchLogsBySearchTerms(String searchTerms);
}

