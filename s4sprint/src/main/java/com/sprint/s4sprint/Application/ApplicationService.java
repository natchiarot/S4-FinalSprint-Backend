package com.sprint.s4sprint.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    public Application getApplication(long index) {
        Optional<Application> result = applicationRepository.findById(index);

        if (result.isPresent()) {
            return result.get();
        }

        return null;
    }

    public Application createApplication(Application newApplication) {
        return applicationRepository.save(newApplication);
    }

    public List<Application> getAllApplications() {
        return (List<Application>) applicationRepository.findAll();
    }

    public Application updateApplication(Integer index, Application updatedApplication) {
        Application applicationToUpdate = getApplication(index);

        applicationToUpdate.setApplicantId(updatedApplication.getApplicantId());

        return applicationRepository.save(applicationToUpdate);
    }

    public void deleteApplication(long index) {
        applicationRepository.delete(getApplication(index));
    }

    public List<Application> findApplicationsByApplicationStatusAndJobId(String applicationStatus, long jobId) {
        return applicationRepository.findApplicationsByApplicationStatusAndJobId(applicationStatus, jobId);
    }
}
