package com.sprint.s4sprint.Application;

import com.sprint.s4sprint.Applicant.Applicant;
import com.sprint.s4sprint.JobPosting.JobPosting;
import com.sprint.s4sprint.Resume.Resume;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Application {
    @Id
    @SequenceGenerator(name = "application_sequence", sequenceName = "application_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "application_sequence")

    private long applicationId;
    private String applicationStatus;
    private LocalDateTime lastUpdated;
    private List<JobPosting> jobId;
    private List<Applicant> applicantId;
    private List<Resume> resumeId;

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<JobPosting> getJobId() {
        return jobId;
    }

    public void setJobId(List<JobPosting> jobId) {
        this.jobId = jobId;
    }

    public List<Applicant> getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(List<Applicant> applicantId) {
        this.applicantId = applicantId;
    }

    public List<Resume> getResumeId() {
        return resumeId;
    }

    public void setResumeId(List<Resume> resumeId) {
        this.resumeId = resumeId;
    }
}
