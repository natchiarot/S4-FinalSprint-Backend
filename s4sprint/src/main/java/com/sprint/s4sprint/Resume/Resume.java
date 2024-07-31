package com.sprint.s4sprint.Resume;

import com.sprint.s4sprint.Applicant.Applicant;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Resume {
    @Id
    @SequenceGenerator(name = "resume_sequence", sequenceName = "resume_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "resume_sequence")
    private long resumeId;
    private LocalDateTime dateSubmitted;
    private String resumeText;
    private String reviewNotes;
    private String location;
    @ManyToOne
    private Applicant applicantId;

    public long getResumeId() {
        return resumeId;
    }

    public void setResumeId(long resumeId) {
        this.resumeId = resumeId;
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

    public Applicant getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Applicant applicantId) {
        this.applicantId = applicantId;
    }
}
