package com.sprint.s4sprint;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Applicant {
    @Id
    @SequenceGenerator(name = "applicant_sequence", sequenceName = "applicant_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "applicant_sequence")
    private long applicantId;
    private String applicantName;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime lastUpdated;

    public long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(long applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}