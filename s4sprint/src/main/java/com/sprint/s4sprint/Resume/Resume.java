package com.sprint.s4sprint.Resume;

import com.sprint.s4sprint.Applicant.Applicant;
import com.sprint.s4sprint.Application.Application;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Resume {
    @Id
    @SequenceGenerator(name = "resume_sequence", sequenceName = "resume_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "resume_sequence")
    private long resumeId;
    @ManyToOne
    @JoinColumn(name = "applicationId")
    private Application application;
    private LocalDateTime dateSubmitted;
    private String resumeText;
    private String reviewNotes;
    private String location;
    @ManyToOne
    @JoinColumn(name="applicantId", nullable = false)
    private Applicant applicant;

    public long getResumeId() {
        return resumeId;
    }

    public void setResumeId(long resumeId) {
        this.resumeId = resumeId;
    }

    public Application getApplicationId() {
        return application;
    }

    public void setApplicationId(Application application) {
        this.application = application;
    }

    public LocalDateTime getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDateTime dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public String getReviewNotes() {
        return reviewNotes;
    }

    public void setReviewNotes(String reviewNotes) {
        this.reviewNotes = reviewNotes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
}
