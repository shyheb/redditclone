package com.example.springredditclone.service;

import com.example.springredditclone.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostDto getPost(Long id);
    List<PostDto> getAllPosts();
    List<PostDto> getPostsBySubbredit(Long id);
    List<PostDto> getPostsByUsername(String username);
}
