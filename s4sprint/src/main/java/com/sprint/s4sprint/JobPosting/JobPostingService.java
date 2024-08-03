package com.sprint.s4sprint.JobPosting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobPostingService {
    @Autowired
    private JobPostingRepository jobPostingRepository;

    public JobPosting getJobPosting(long index) {
        Optional<JobPosting> result = jobPostingRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public JobPosting createJobPosting(JobPosting newJobPosting) {
        return jobPostingRepository.save(newJobPosting);
    }

    public List<JobPosting> getAllJobPostings() {
        return (List<JobPosting>) jobPostingRepository.findAll();
    }

    public JobPosting updateJobPosting(Integer index, JobPosting updatedJobPosting) {
        JobPosting jobPostingToUpdate = getJobPosting(index);

        jobPostingToUpdate.setPosition(updatedJobPosting.getPosition());
        jobPostingToUpdate.setCompanyName(updatedJobPosting.getCompanyName());
        jobPostingToUpdate.setEmploymentType(updatedJobPosting.getEmploymentType());
        jobPostingToUpdate.setJobDescription(updatedJobPosting.getJobDescription());
        jobPostingToUpdate.setExperienceLevel(updatedJobPosting.getExperienceLevel());
        jobPostingToUpdate.setRequiredSkills(updatedJobPosting.getRequiredSkills());
        jobPostingToUpdate.setCompensation(updatedJobPosting.getCompensation());
        jobPostingToUpdate.setBenefits(updatedJobPosting.getBenefits());
        jobPostingToUpdate.setWorkLocation(updatedJobPosting.getWorkLocation());
        jobPostingToUpdate.setApplicationDeadline(updatedJobPosting.getApplicationDeadline());

        return jobPostingRepository.save(jobPostingToUpdate);
    }

    public void deleteJobPosting(long index) {
        jobPostingRepository.delete(getJobPosting(index));
    }

    public List<JobPosting> findJobPostingsByPositionAndCompanyName(String position, String companyName) {
        return jobPostingRepository.findJobPostingByPositionAndCompanyName(position, companyName);
    }
}
