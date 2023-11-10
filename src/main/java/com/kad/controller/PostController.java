package com.kad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kad.entity.Post;
import com.kad.service.PostService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    @Autowired
    private PostService psr;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Post p){
        return new ResponseEntity<>(psr.createPost(p),HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody Post p){
        return new ResponseEntity<>(psr.updatePost(p),HttpStatus.OK);
    }

    @GetMapping("/getAllPostsByTime")
    public ResponseEntity<?> getAllByDate( ){
        return new ResponseEntity<>(psr.getAllPostsByDate(),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(psr.getAllPosts(),HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<?> deletePost(@PathVariable long postId){
        psr.deletePost(postId);
        String msg="{\"message\": \"Success\"}";
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }


}
