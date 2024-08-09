package com.sprint.s4sprint.SearchLogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SearchLogsController {
    @Autowired
    private SearchLogsService searchLogsService;

    @GetMapping("search_search_logs")
    public SearchLogs searchSearchLogs(@RequestParam(value = "searchTerms", required = false) String searchTerms) {
        return searchLogsService.findSearchLoggingsBySearchTerms(searchTerms);
    }

    @GetMapping("search_loggings")
    public List<SearchLogs> getAllSearchLoggings() {
        return searchLogsService.getAllSearchLoggings();
    }

    @GetMapping("search_logs/{index}")
    public SearchLogs getSearchLogs(@PathVariable Integer index) {
        return searchLogsService.getSearchLogs(index);
    }

    @PostMapping("search_logs")
    public SearchLogs createSearchLogs(@RequestBody SearchLogs newSearchLogs) {
        return searchLogsService.createSearchLogs(newSearchLogs);
    }

    @DeleteMapping("search_logs/{index}")
    public void deleteSearchLogs(@PathVariable Integer index) {
        searchLogsService.deleteSearchLogs(index);
    }
}