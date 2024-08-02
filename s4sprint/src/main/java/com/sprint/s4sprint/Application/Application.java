package com.sprint.s4sprint.Application;

import com.sprint.s4sprint.Applicant.Applicant;
import com.sprint.s4sprint.JobPosting.JobPosting;
//import com.sprint.s4sprint.Resume.Resume;
import com.sprint.s4sprint.Resume.Resume;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Application {
    @Id
    @SequenceGenerator(name = "application_sequence", sequenceName = "application_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "application_sequence")
    private long application;
    private String applicationStatus;
    private LocalDateTime lastUpdated;
    @OneToMany(mappedBy = "application")
    private List<JobPosting> jobPostings;
    @ManyToOne
    @JoinColumn(name="applicantId", nullable = false)
    private Applicant applicant;
    @OneToOne(mappedBy = "application")
    private Resume resume;

    public long getApplicationId() {
        return application;
    }

    public void setApplicationId(long application) {
        this.application = application;
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

    public List<JobPosting> getJobPostings() {
        return jobPostings;
    }

    public void setJobId(List<JobPosting> jobPostings) {
        this.jobPostings = jobPostings;
    }

    public Applicant getApplicantId() {
        return applicant;
    }

    public void setApplicantId(Applicant applicantId) {
        this.applicant = applicant;
    }

    public Resume getResumeId() {
        return resume;
    }

    public void setResumeId(Resume resume) {
        this.resume = resume;
    }
}
