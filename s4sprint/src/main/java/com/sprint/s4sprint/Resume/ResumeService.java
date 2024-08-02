package com.sprint.s4sprint.Resume;

import com.sprint.s4sprint.Applicant.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    public Resume getResume(long index) {
        Optional<Resume> result = resumeRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public Resume createResume(Resume newResume) {
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

    public Resume findByApplicant(Applicant applicant) {
        return resumeRepository.findByApplicant(applicant);
    }
}
