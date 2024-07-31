package com.sprint.s4sprint.Resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @GetMapping("search_resume")
    public List<Resume> searchResume(@RequestParam(value = "applicantId", required = true) long applicantId) {
        return resumeService.findResumesByApplicantId(applicantId);
    }

    @GetMapping("resumes")
    public List<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("resume/{index}")
    public Resume getResume(@PathVariable Integer index) {
        return resumeService.getResume(index);
    }

    @PostMapping("resume")
    public Resume createResume(@RequestBody Resume newResume) {
        return resumeService.createResume(newResume);
    }

    @PutMapping("resume/{index}")
    public Resume updateResume(@PathVariable Integer index, @RequestBody Resume updatedResume) {
        return resumeService.updateResume(index, updatedResume);
    }

    @DeleteMapping("resume/{index}")
    public void deleteResume(@PathVariable Integer index) {
        resumeService.deleteResume(index);
    }
}