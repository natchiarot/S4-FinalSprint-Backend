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
                } else {
                    Optional<JobPosting> existingJobPosting = jobPostingRepository.findById(jobPosting.getJobId());
                    if (existingJobPosting.isPresent()) {
                        jobPostingRepository.save(jobPosting);
                    } else {
                        jobPostingRepository.save(jobPosting);
                    }
                }
            }
        }

        if (newApplication.getResume() == null) {
            System.out.println("No resume entered");
            return null;
        } else {
            Resume resume = newApplication.getResume();

            if (resume.getApplicant() != null) {
                Applicant applicant = resume.getApplicant();
                Optional<Applicant> applicantInDB = applicantRepository.findApplicantsByApplicantName(applicant.getApplicantName());

                if (applicantInDB.isEmpty()) {
                    applicant = applicantRepository.save(applicant);
                } else {
                    applicant = applicantInDB.get();
                }
                resume.setApplicant(applicant);
            } else {
                System.out.println("No applicant associated with resume");
                return null;
            }

            Optional<Resume> resumeInDB = resumeRepository.findByResumeText(resume.getResumeText());
            if (resumeInDB.isEmpty()) {
                resume = resumeRepository.save(resume);
            } else {
                resume = resumeInDB.get();
            }
            newApplication.setResume(resume);
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
//            applicationToUpdate.setApplicant(updatedApplication.getApplicant());
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
