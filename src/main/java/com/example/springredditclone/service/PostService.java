package com.example.springredditclone.service;

import com.example.springredditclone.dto.PostDto;
import com.example.springredditclone.model.Post;

import java.util.List;

public interface PostService {
    Post createPost(PostDto postDto);
    Post getPost(Long id);
    List<Post> getAllPosts();
    List<Post> getPostsBySubbredit(Long id);
    List<Post> getPostsByEmail(String email);
}
