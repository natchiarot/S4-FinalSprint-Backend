package com.sprint.s4sprint.Applicant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

    @GetMapping("search_applicant")
    public Applicant searchApplicant(@RequestParam(value = "applicantName", required = false) String applicantName) {
        return applicantService.findApplicantsByApplicantName(applicantName);
    }

    @GetMapping("applicants")
    public List<Applicant> getAllApplicants() {
        return applicantService.getAllApplicants();
    }

    @GetMapping("applicant/{index}")
    public Applicant getApplicant(@PathVariable long index) {
        return applicantService.getApplicant(index);
    }

    @PostMapping("applicant")
    public Applicant createApplicant(@RequestBody Applicant newApplicant) {
        return applicantService.createApplicant(newApplicant);
    }

    @PutMapping("applicant/{index}")
    public Applicant updateApplicant(@PathVariable long index, @RequestBody Applicant updatedApplicant) {
        return applicantService.updateApplicant(index, updatedApplicant);
    }

    @DeleteMapping("applicant/{index}")
    public void deleteApplicant(@PathVariable long index) {
        applicantService.deleteApplicant(index);
    }
}

