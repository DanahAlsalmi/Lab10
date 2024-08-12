package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.JopApplicationRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JopApplicationRepository applicationRepository;
    private final JobPostRepository postRepository;
    private final UserRepository userRepository;

    //get
    public List<JobApplication> getAllJobApplication(){
        return applicationRepository.findAll();
    }

    //Apply for a job
    public boolean addJobApplication(JobApplication jobApplication) {
        boolean postExists = postRepository.existsById(jobApplication.getJobPostId());
        boolean userExists = userRepository.existsById(jobApplication.getUserId());

        if (postExists && userExists) {
            applicationRepository.save(jobApplication);
            return true;
        } else {
            return false;
        }
    }


    //Withdraw
    public boolean withdrawJobApplication(Integer id) {
        JobApplication application = applicationRepository.getReferenceById(id);
        if (application != null) {
            applicationRepository.delete(application);
            return true;
        } else {
            return false;
        }
    }


}
