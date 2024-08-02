package com.sprint.s4sprint.Resume;

import com.sprint.s4sprint.Applicant.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sprint.s4sprint.Applicant.ApplicantService;

import java.util.List;

@RestController
@CrossOrigin
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("search_resume")
    public Resume searchResume(@RequestParam(value = "applicantId", required = false) String resumeText) {
        return resumeService.findByResumeText(resumeText);
    }

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
        Long applicantId = newResume.getApplicant().getApplicantId();
        Applicant applicant = applicantService.getApplicant(applicantId);

        newResume.setApplicant(applicant);

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
}