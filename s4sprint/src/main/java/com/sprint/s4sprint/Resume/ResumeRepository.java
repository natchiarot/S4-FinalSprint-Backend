package com.sprint.s4sprint.Resume;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends CrudRepository<Resume, Long>  {
    Optional<Resume> findByResumeText(String resumeText);
    List<Resume> findByResumeTextContaining(String resumeText);
}

