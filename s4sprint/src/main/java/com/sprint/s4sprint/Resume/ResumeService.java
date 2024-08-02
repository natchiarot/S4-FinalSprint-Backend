package com.sprint.s4sprint.Resume;

import com.sprint.s4sprint.Applicant.Applicant;
import com.sprint.s4sprint.Applicant.ApplicantRepository;
import com.sprint.s4sprint.JobPosting.JobPosting;
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
        } else {
            Applicant applicant = newResume.getApplicant();
            Applicant applicantInDB = applicantRepository.findApplicantsByApplicantName(applicant.getApplicantName());
                if (applicantInDB == null) {
                    applicant = applicantRepository.save(applicant);
                }
            }
        return resumeRepository.save(newResume);
    }

    public List<Resume> getAllResumes() {
        return (List<Resume>) resumeRepository.findAll();
    }

    public Resume updateResume(long index, Resume updatedResume) {
        Resume resumeToUpdate = getResume(index);

        resumeToUpdate.setApplicant(updatedResume.getApplicant());

        return resumeRepository.save(resumeToUpdate);
    }

    public void deleteResume(long index) {
        resumeRepository.delete(getResume(index));
    }

    public Resume findByResumeText(String resumeText) {
        return resumeRepository.findByResumeText(resumeText);
    }
}
