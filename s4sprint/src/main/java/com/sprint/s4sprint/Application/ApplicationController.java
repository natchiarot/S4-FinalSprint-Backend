package com.sprint.s4sprint.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("search_application")
    public List<Application> searchApplication(@RequestParam(value = "applicationStatus", required = false) String applicationStatus, @RequestParam(value = "jobId", required = false) long jobId) {
        return applicationService.findApplicationsByApplicationStatusAndJobId(applicationStatus, jobId);
    }

    @GetMapping("applications")
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("application/{index}")
    public Application getApplication(@PathVariable Integer index) {
        return applicationService.getApplication(index);
    }

    @PostMapping("application")
    public Application createApplication(@RequestBody Application newApplication) {
        return applicationService.createApplication(newApplication);
    }

    @PutMapping("application/{index}")
    public Application updateApplication(@PathVariable Integer index, @RequestBody Application updatedApplication) {
        return applicationService.updateApplication(index, updatedApplication);
    }

    @DeleteMapping("application/{index}")
    public void deleteApplication(@PathVariable Integer index) {
        applicationService.deleteApplication(index);
    }
}