package com.sprint.s4sprint.Resume;

import com.sprint.s4sprint.Applicant.ApplicantRepository;
import com.sprint.s4sprint.Forms.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    public Resume getResume(long index) {
        Optional<Resume> result = resumeRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public Resume createResume(Resume newResume) {
        if (newResume.getApplicant() == null) {
            System.out.println("No applicant entered");
            return null;
        }
        return resumeRepository.save(newResume);
    }

    public List<Resume> getAllResumes() {
        return (List<Resume>) resumeRepository.findAll();
    }

    public Resume updateResume(long index, Resume updatedResume) {
        Resume resumeToUpdate = getResume(index);

        resumeToUpdate.setDateSubmitted(updatedResume.getDateSubmitted());
        resumeToUpdate.setResumeText(updatedResume.getResumeText());
        resumeToUpdate.setReviewNotes(updatedResume.getReviewNotes());
        resumeToUpdate.setLocation(updatedResume.getLocation());
        resumeToUpdate.setApplicant(updatedResume.getApplicant());

        return resumeRepository.save(resumeToUpdate);
    }

    public void deleteResume(long index) {
        resumeRepository.delete(getResume(index));
    }

    // Search resumes. Supports multiple keywords - I was not sure how to implement this using only Hibernate,
    //  so multi-keyword searches involve post-processing of the data retrieved from the database
    public List<Resume> searchResumes(SearchForm searchForm) {
        String[] searchTerms = searchForm.getQuery().split(" +");
        List<Resume> results = resumeRepository.findByResumeTextContainingIgnoreCase(searchTerms[0]);

        for (int i = 1; i < searchTerms.length; i++) {
            int current = i;
            results.removeIf(resume -> !resume.getResumeText().toLowerCase().contains(searchTerms[current].toLowerCase()));
        }

        return results;
    }
}
