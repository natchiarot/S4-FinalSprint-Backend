package com.sprint.s4sprint.Application;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long>  {
    public Application findApplicationsByApplicationStatus(String applicationStatus);
}

