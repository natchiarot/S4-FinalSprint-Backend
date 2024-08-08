package com.sprint.s4sprint.SearchLogs;

import com.sprint.s4sprint.User.User;
import com.sprint.s4sprint.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchLogsService {
    @Autowired
    private SearchLogsRepository searchLogsRepository;

    @Autowired
    private UserRepository userRepository;

    public SearchLogs getSearchLogs(long index) {
        Optional<SearchLogs> result = searchLogsRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public SearchLogs createSearchLogs(SearchLogs newSearchLogs) {
        if (newSearchLogs.getUser() != null) {
           User user = newSearchLogs.getUser();
            List<User> userInDB = userRepository.findByUserName(user.getUserName());

            if (userInDB.isEmpty()) {
                user = userRepository.save(user);
            } else {
                user = userInDB.get(0);
            }
            newSearchLogs.setUser(user);
        }
        return searchLogsRepository.save(newSearchLogs);
    }

    public List<SearchLogs> getAllSearchLoggings() {
        return (List<SearchLogs>) searchLogsRepository.findAll();
    }

    public SearchLogs updateSearchLogs(Integer index, SearchLogs updatedSearchLogs) {
        SearchLogs searchLogsToUpdate = getSearchLogs(index);

        searchLogsToUpdate.setSearchDateTime(updatedSearchLogs.getSearchDateTime());
        searchLogsToUpdate.setSearchTerms(updatedSearchLogs.getSearchTerms());
        searchLogsToUpdate.setUser(updatedSearchLogs.getUser());

        return searchLogsRepository.save(searchLogsToUpdate);
    }

    public void deleteSearchLogs(long index) {
        searchLogsRepository.delete(getSearchLogs(index));
    }

    public SearchLogs findSearchLoggingsBySearchTerms(String searchTerms) {
        return searchLogsRepository.findSearchLoggingsBySearchTerms(searchTerms);
    }
}
