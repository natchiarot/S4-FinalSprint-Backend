package com.sprint.s4sprint.SearchLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SearchLogController {
    @Autowired
    private SearchLogService searchLogService;

    @GetMapping("find_search_logs")
    public List<SearchLog> searchSearchLogs(@RequestParam(value = "searchTerms", required = false) String searchTerms) {
        return searchLogService.findSearchLogsBySearchTerms(searchTerms);
    }

    @GetMapping("search_log/{index}")
    public SearchLog getSearchLogs(@PathVariable Integer index) {
        return searchLogService.getSearchLog(index);
    }

    @GetMapping("search_logs")
    public List<SearchLog> getAllSearchLogs() {
        return searchLogService.getAllSearchLogs();
    }

    @PostMapping("search_log")
    public SearchLog createSearchLog(@RequestBody SearchLog newSearchLog) {
        return searchLogService.createSearchLog(newSearchLog);
    }

    @DeleteMapping("search_log/{index}")
    public void deleteSearchLog(@PathVariable Integer index) {
        searchLogService.deleteSearchLog(index);
    }
}