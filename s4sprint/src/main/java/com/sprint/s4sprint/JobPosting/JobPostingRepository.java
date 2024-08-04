package com.sprint.s4sprint.JobPosting;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostingRepository extends CrudRepository<JobPosting, Long>  {
    public List<JobPosting> findJobPostingByPositionAndCompanyName(String position, String companyName);
}
