package com.sprint.s4sprint.Application;

import com.sprint.s4sprint.Applicant.ApplicantRepository;
import com.sprint.s4sprint.JobPosting.JobPostingRepository;
import com.sprint.s4sprint.Resume.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    public Application getApplication(long index) {
        Optional<Application> result = applicationRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public Application createApplication(Application newApplication) {
        if (newApplication.getJobPostings() == null) {
            System.out.println("No job posting entered");
            return null;
        }

        if (newApplication.getResume() == null) {
            System.out.println("No resume entered");
            return null;
        }

        if (newApplication.getResume().getApplicant() == null) {
            System.out.println("No applicant for resume entered");
            return null;
        }

            return applicationRepository.save(newApplication);
        }

        public List<Application> getAllApplications () {
            return (List<Application>) applicationRepository.findAll();
        }

        public Application updateApplication (Integer index, Application updatedApplication){
            Application applicationToUpdate = getApplication(index);

            applicationToUpdate.setApplicationStatus(updatedApplication.getApplicationStatus());
            applicationToUpdate.setLastUpdated(updatedApplication.getLastUpdated());
            applicationToUpdate.setJobPostings(updatedApplication.getJobPostings());
            applicationToUpdate.setResume(updatedApplication.getResume());

            return applicationRepository.save(applicationToUpdate);
        }

        public void deleteApplication ( long index){
            applicationRepository.delete(getApplication(index));
        }

        public Application findApplicationsByApplicationStatus (String applicationStatus){
            return applicationRepository.findApplicationsByApplicationStatus(applicationStatus);
        }
    }
