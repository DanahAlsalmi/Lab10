package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-post")
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity getJobPost(){
        return ResponseEntity.status(200).body(jobPostService.getAllJobPost());
    }

    @PostMapping("/add")
    public ResponseEntity addJobPost(@Valid @RequestBody JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(msg);
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(201).body(new ApiResponse("Job Post Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@Valid @RequestBody JobPost jobPost, @PathVariable Integer id,Errors errors){
        if(errors.hasErrors()){
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(msg);
        }
        boolean isUpdated = jobPostService.updateJobPost(id, jobPost);
        if(isUpdated){
            return ResponseEntity.status(201).body(new ApiResponse("Job Post Updated Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Job Post Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id){
        boolean isDeleted = jobPostService.deleteJobPost(id);
        if(isDeleted){
            return ResponseEntity.status(201).body(new ApiResponse("Job Post Deleted Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Job Post Not Found"));
    }
}
