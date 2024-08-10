package com.sprint.s4sprint.SearchLog;

import com.sprint.s4sprint.User.User;
import com.sprint.s4sprint.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchLogService {
    @Autowired
    private SearchLogRepository searchLogsRepository;

    @Autowired
    private UserRepository userRepository;

    public SearchLog getSearchLog(long index) {
        Optional<SearchLog> result = searchLogsRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public SearchLog createSearchLog(SearchLog newSearchLog) {
        if (newSearchLog.getUser() != null) {
           User user = newSearchLog.getUser();
            Optional<User> userInDB = userRepository.findByUserName(user.getUserName());

            if (userInDB.isEmpty()) {
                user = userRepository.save(user);
            } else {
                user = userInDB.get();
            }
            newSearchLog.setUser(user);
        }
        return searchLogsRepository.save(newSearchLog);
    }

    public List<SearchLog> getAllSearchLogs() {
        return (List<SearchLog>) searchLogsRepository.findAll();
    }

    public void deleteSearchLog(long index) {
        searchLogsRepository.delete(getSearchLog(index));
    }

    public List<SearchLog> findSearchLogsBySearchTerms(String searchTerms) {
        return searchLogsRepository.findSearchLogsBySearchTerms(searchTerms);
    }
}
