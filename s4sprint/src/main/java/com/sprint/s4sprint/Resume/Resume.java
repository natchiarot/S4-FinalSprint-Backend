package com.sprint.s4sprint.Resume;

import com.sprint.s4sprint.Applicant.Applicant;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Resume {
    @Id
    @SequenceGenerator(name = "resume_sequence", sequenceName = "resume_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "resume_sequence")
    private long resumeId;
    private String dateSubmitted;
    private String resumeText;
    private String reviewNotes;
    private String location;
    private List<Applicant> applicantId;

    public long getResumeId() {
        return resumeId;
    }

    public void setResumeId(long resumeId) {
        this.resumeId = resumeId;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(String dateSubmitted) {
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

    public List<Applicant> getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(List<Applicant> applicantId) {
        this.applicantId = applicantId;
    }
}
