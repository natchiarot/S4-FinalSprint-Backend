package com.sprint.s4sprint.JobPosting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class JobPostingController {
    @Autowired
    private JobPostingService jobPostingService;

    @GetMapping("search_job_posting")
    public List<JobPosting> searchJobPosting(@RequestParam(value = "position", required = true) String jobPostingName, @RequestParam(value = "companyName", required = true) String companyName) {
        return jobPostingService.findJobPostingsByPositionAndCompanyName(jobPostingName, companyName);
    }

    @GetMapping("job_postings")
    public List<JobPosting> getAllJobPostings() {
        return jobPostingService.getAllJobPostings();
    }

    @GetMapping("job_posting/{index}")
    public JobPosting getJobPosting(@PathVariable Integer index) {
        return jobPostingService.getJobPosting(index);
    }

    @PostMapping("job_posting")
    public JobPosting createJobPosting(@RequestBody JobPosting newJobPosting) {
        return jobPostingService.createJobPosting(newJobPosting);
    }

    @PutMapping("job_posting/{index}")
    public JobPosting updateJobPosting(@PathVariable Integer index, @RequestBody JobPosting updatedJobPosting) {
        return jobPostingService.updateJobPosting(index, updatedJobPosting);
    }

    @DeleteMapping("job_posting/{index}")
    public void deleteJobPosting(@PathVariable Integer index) {
        jobPostingService.deleteJobPosting(index);
    }
}
