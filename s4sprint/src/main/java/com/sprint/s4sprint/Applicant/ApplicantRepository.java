package com.sprint.s4sprint.Applicant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, Long>  {
    public Applicant findApplicantsByApplicantName(String applicantName);
}
