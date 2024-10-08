package com.sprint.s4sprint.Resume;

import com.sprint.s4sprint.Forms.SearchForm;
import com.sprint.s4sprint.SearchLog.SearchLog;
import com.sprint.s4sprint.SearchLog.SearchLogService;
import com.sprint.s4sprint.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.s4sprint.Applicant.ApplicantService;

import java.util.*;

@RestController
@CrossOrigin
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    private SearchLogService searchLogService;

    @Autowired
    private UserService userService;

    @GetMapping("resumes")
    public List<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("resume/{index}")
    public Resume getResume(@PathVariable long index) {
        return resumeService.getResume(index);
    }

    @PostMapping("resume")
    public Resume createResume(@RequestBody Resume newResume) {
        return resumeService.createResume(newResume);
    }

    @PutMapping("resume/{index}")
    public Resume updateResume(@PathVariable long index, @RequestBody Resume updatedResume) {
        return resumeService.updateResume(index, updatedResume);
    }

    @DeleteMapping("resume/{index}")
    public void deleteResume(@PathVariable long index) {
        resumeService.deleteResume(index);
    }

    @PostMapping("resumes/search")
    public List<Resume> searchResumes(@RequestBody SearchForm searchForm) {
        if (searchForm.getQuery().isEmpty())
            return new ArrayList<>();

        // Create a new search log entry
        SearchLog newLog = new SearchLog();
        newLog.setSearchDateTime(new Date());
        newLog.setSearchTerms(searchForm.getQuery());
        newLog.setUser(userService.getUser(searchForm.getUsername()));

        searchLogService.createSearchLog(newLog);

        return resumeService.searchResumes(searchForm);
    }
}