package com.sprint.s4sprint.Resume;

import com.sprint.s4sprint.Applicant.Applicant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends CrudRepository<Resume, Long>  {
    public Resume findByApplicant(Applicant applicant);
}

