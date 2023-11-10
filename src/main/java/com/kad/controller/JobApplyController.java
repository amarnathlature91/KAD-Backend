package com.kad.controller;

import com.kad.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
public class JobApplyController {

    @Autowired
    private EmailUtils emu;

    @PostMapping("/applyJob")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("jobTitle") String jobTitle,
                                                   @RequestParam("skills") String skills,
                                                   @RequestParam("experience") String experience,
                                                   @RequestParam("fullName")String fullName,
                                                   @RequestParam("description") String description) {

        try {
            byte[] fileBytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            emu.sendEmailWithAttachment(jobTitle, description, fileBytes, fileName,fullName,experience,skills);
            return ResponseEntity.ok("File uploaded and sent via email successfully");
        } catch (IOException | MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading and sending the file");
        }
    }
}
