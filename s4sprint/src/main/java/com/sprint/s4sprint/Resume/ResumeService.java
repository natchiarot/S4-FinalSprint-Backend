package com.sprint.s4sprint.Resume;

import com.sprint.s4sprint.Applicant.Applicant;
import com.sprint.s4sprint.Applicant.ApplicantRepository;
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
        if (newResume.getApplicant() != null) {
            Applicant applicant = newResume.getApplicant();
            Optional<Applicant> applicantInDB = applicantRepository.findApplicantsByApplicantName(applicant.getApplicantName());

            if (applicantInDB.isEmpty()) {
                applicant = applicantRepository.save(applicant);
            } else {
                applicant = applicantInDB.get();
            }
            newResume.setApplicant(applicant);
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

    public Optional<Resume> findByResumeText(String resumeText) {
        return resumeRepository.findByResumeText(resumeText);
    }

    public List<Resume> findByResumeTextLike(String resumeText) {
        return resumeRepository.findByResumeTextContaining(resumeText);
    }
}
