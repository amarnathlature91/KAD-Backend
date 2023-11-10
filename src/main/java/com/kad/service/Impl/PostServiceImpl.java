package com.kad.service.Impl;

import com.kad.entity.Post;
import com.kad.repository.PostRepository;
import com.kad.repository.UserRepository;
import com.kad.service.PostService;
import com.kad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository ptr;

    @Autowired
    private UserService usr;

    @Override
    public Post createPost(Post post) {
        Post p = new Post();
        p.setJobTitle(post.getJobTitle());
        p.setLocation(post.getLocation());
        p.setSalary(post.getSalary());
        p.setExperience(post.getExperience());
        p.setDescription(post.getDescription());
        p.setUser(usr.getAuthenticatedUser());
        p.setDateLastModified(new Date());
        return ptr.save(p);
    }

    @Override
    public void deletePost(long postId) {
        ptr.deleteById(postId);
    }

    @Override
    public Post updatePost(Post post) {
        Post p = getPost(post.getPostId());
        p.setJobTitle(post.getJobTitle());
        p.setLocation(post.getLocation());
        p.setSalary(post.getSalary());
        p.setExperience(post.getExperience());
        p.setDescription(post.getDescription());
        p.setUser(usr.getAuthenticatedUser());
        p.setDateLastModified(new Date());
        return ptr.save(p);
    }

    @Override
    public Post getPost(long postId) {
        return ptr.findById(postId).orElseThrow(() -> new RuntimeException("Post Not Found With ID"));
    }

    @Override
    public List<Post> getAllPostsByDate() {
        return ptr.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "dateLastModified"))).stream().collect(Collectors.toList());
    }

    @Override
    public List<Post> getAllPosts(){
        return ptr.findAll();
    }




}
