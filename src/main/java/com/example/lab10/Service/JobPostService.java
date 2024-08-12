package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

    private final JobPostRepository jobPostRepository;

    //get all
    public List<JobPost> getAllJobPost(){
        return jobPostRepository.findAll();
    }

    //Add
    public void addJobPost(JobPost jobPost){
        jobPost.setPostingDate(LocalDate.now());
        jobPostRepository.save(jobPost);
    }

    //Update
    public boolean updateJobPost(Integer id ,JobPost jobPost){
        JobPost jp = jobPostRepository.findById(id).get();
        if(jp != null){
            jp.setTitle(jobPost.getTitle());
            jp.setDescription(jobPost.getDescription());
            jp.setLocation(jobPost.getLocation());
            jp.setSalary(jobPost.getSalary());
            jp.setPostingDate(LocalDate.now());
            jobPostRepository.save(jp);
            return true;
        }
        return false;
    }

    //Delete
    public boolean deleteJobPost(Integer id){
        JobPost jp = jobPostRepository.findById(id).get();
        if(jp != null){
            jobPostRepository.delete(jp);
            return true;
        }
        return false;
    }
}
