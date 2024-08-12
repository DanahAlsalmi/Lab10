package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/job-application")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getJobApplication(){
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplication());
    }

    @PostMapping("/add")
    public ResponseEntity addJobApplication(@Valid @RequestBody JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getAllErrors());
        }
        jobApplicationService.addJobApplication(jobApplication);
        return ResponseEntity.status(201).body(new ApiResponse("Job Application Added Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobApplication(@PathVariable Integer id){
        boolean isDeleted= jobApplicationService.withdrawJobApplication(id);
        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Job Application Deleted Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("Job Application Not Found"));
    }

}
