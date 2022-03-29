package com.example.springredditclone.controller;

import com.example.springredditclone.dto.PostDto;
import com.example.springredditclone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllPosts(@PathVariable Long id){
        return new ResponseEntity<>(postService.getPost(id),HttpStatus.OK);
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<?> getPostsBySubredditId(@PathVariable Long id){
        return new ResponseEntity<>(postService.getPostsBySubbredit(id),HttpStatus.OK);
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<?> getPostsByUsername(@PathVariable String username){
        return new ResponseEntity<>(postService.getPostsByUsername(username),HttpStatus.OK);
    }
}
