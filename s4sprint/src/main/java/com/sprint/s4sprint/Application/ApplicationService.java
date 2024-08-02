package com.sprint.s4sprint.Application;

import com.sprint.s4sprint.Applicant.Applicant;
import com.sprint.s4sprint.Applicant.ApplicantRepository;
import com.sprint.s4sprint.JobPosting.JobPosting;
import com.sprint.s4sprint.JobPosting.JobPostingRepository;
import com.sprint.s4sprint.Resume.Resume;
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
        if (newApplication.getJobPostings() != null) {
            for (JobPosting jobPosting : newApplication.getJobPostings()) {
                if (jobPosting.getJobId() == 0) {
                    jobPostingRepository.save(jobPosting);
                }
            }
        }

        if (newApplication.getApplicant() != null) {
            Applicant applicant = newApplication.getApplicant();
            Applicant applicantInDB = applicantRepository.findApplicantsByApplicantName(applicant.getApplicantName());

            if (applicantInDB == null) {
                applicant = applicantRepository.save(applicant);
            } else {
                applicant = applicantInDB;
            }
            newApplication.setApplicant(applicant);
        } else {
            System.out.println("No applicant entered");
        }

            if (newApplication.getResume() == null) {
                System.out.println("No resume entered");
            } else {
                Resume resume = newApplication.getResume();

                if (resume.getApplicant() == null) {
                    System.out.println("No applicant associated with resume");
                    return null;
                }
                Resume resumeInDB = resumeRepository.findByResumeText(resume.getResumeText());

                if (resumeInDB == null) {
                    resume = resumeRepository.save(resume);
                    newApplication.setResume(resume);
                } else {
                    newApplication.setResume(resumeInDB);
                }
            }

            return applicationRepository.save(newApplication);
        }

        public List<Application> getAllApplications () {
            return (List<Application>) applicationRepository.findAll();
        }

        public Application updateApplication (Integer index, Application updatedApplication){
            Application applicationToUpdate = getApplication(index);

            applicationToUpdate.setApplicationId(updatedApplication.getApplicationId());

            return applicationRepository.save(applicationToUpdate);
        }

        public void deleteApplication ( long index){
            applicationRepository.delete(getApplication(index));
        }

        public Application findApplicationsByApplicationStatus (String applicationStatus){
            return applicationRepository.findApplicationsByApplicationStatus(applicationStatus);
        }
    }
