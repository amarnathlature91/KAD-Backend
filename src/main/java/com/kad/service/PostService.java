package com.kad.service;

import com.kad.entity.Post;
import java.util.List;

public interface PostService {

    public Post createPost(Post post);

    public void deletePost(long postId);

    public Post updatePost(Post post);

    public Post getPost(long postId);

    public List<Post> getAllPosts();

    public List<Post> getAllPostsByDate();



}
