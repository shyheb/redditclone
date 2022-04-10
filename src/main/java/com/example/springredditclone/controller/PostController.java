package com.example.springredditclone.controller;

import com.example.springredditclone.dto.PostDto;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.mapper.PostMapper;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/posts/")
@AllArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        Post post = postService.createPost(postDto);
        postDto.setId(post.getId());
        return new ResponseEntity<>(postDto,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        List<PostResponse> postDtoList = postService.getAllPosts()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        PostResponse postDto = postMapper.mapToDto(postService.getPost(id));
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubredditId(@PathVariable Long id){
        List<PostResponse> postDtoList = postService.getPostsBySubbredit(id)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String username){
        List<PostResponse> postDtoList = postService.getPostsByUsername(username)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
}
