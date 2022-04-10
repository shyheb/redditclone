package com.example.springredditclone.controller;

import com.example.springredditclone.dto.PostDto;
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

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        Post post = postService.createPost(postDto);
        postDto.setId(post.getId());
        return new ResponseEntity<>(postDto,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> postDtoList = postService.getAllPosts()
                .stream()
                .map(PostMapper.INSTANCE::mapPostToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id){
        PostDto postDto = PostMapper.INSTANCE.mapPostToDto(postService.getPost(id));
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<PostDto>> getPostsBySubredditId(@PathVariable Long id){
        List<PostDto> postDtoList = postService.getPostsBySubbredit(id)
                .stream()
                .map(PostMapper.INSTANCE::mapPostToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<PostDto>> getPostsByUsername(@PathVariable String username){
        List<PostDto> postDtoList = postService.getPostsByUsername(username)
                .stream()
                .map(PostMapper.INSTANCE::mapPostToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
}
