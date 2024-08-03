package com.sprint.s4sprint.Applicant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, Long>  {
     Optional<Applicant> findApplicantsByApplicantName(String applicantName);
}
