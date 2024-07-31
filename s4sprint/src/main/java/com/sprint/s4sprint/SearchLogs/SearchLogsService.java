package com.sprint.s4sprint.SearchLogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchLogsService {
    @Autowired
    private SearchLogsRepository searchLogsRepository;

    public SearchLogs getSearchLogs(long index) {
        Optional<SearchLogs> result = searchLogsRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public SearchLogs createSearchLogs(SearchLogs newSearchLogs) {
        return searchLogsRepository.save(newSearchLogs);
    }

    public List<SearchLogs> getAllSearchLoggings() {
        return (List<SearchLogs>) searchLogsRepository.findAll();
    }

    public SearchLogs updateSearchLogs(Integer index, SearchLogs updatedSearchLogs) {
        SearchLogs searchLogsToUpdate = getSearchLogs(index);

        searchLogsToUpdate.setSearchTerms(updatedSearchLogs.getSearchTerms());
        searchLogsToUpdate.setUserId(updatedSearchLogs.getUserId());

        return searchLogsRepository.save(searchLogsToUpdate);
    }

    public void deleteSearchLogs(long index) {
        searchLogsRepository.delete(getSearchLogs(index));
    }

    public SearchLogs findSearchLoggingsBySearchTerms(String searchTerms) {
        return searchLogsRepository.findSearchLoggingsBySearchTerms(searchTerms);
    }
}
