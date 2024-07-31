package com.sprint.s4sprint.Applicant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;

    public Applicant getApplicant(long index) {
        Optional<Applicant> result = applicantRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public Applicant createApplicant(Applicant newApplicant) {
        return applicantRepository.save(newApplicant);
    }

    public List<Applicant> getAllApplicants() {
        return (List<Applicant>) applicantRepository.findAll();
    }

    public Applicant updateApplicant(long index, Applicant updatedApplicant) {
        Applicant applicantToUpdate = getApplicant(index);

        applicantToUpdate.setApplicantName(updatedApplicant.getApplicantName());
        applicantToUpdate.setEmail(updatedApplicant.getEmail());

        return applicantRepository.save(applicantToUpdate);
    }

    public void deleteApplicant(long index) {
        applicantRepository.delete(getApplicant(index));
    }

    public Applicant findApplicantsByApplicantName(String applicantName) {
        return applicantRepository.findApplicantsByApplicantName(applicantName);
    }
}
