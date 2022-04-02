package com.example.springredditclone.controller;

import com.example.springredditclone.dto.CommentDto;
import com.example.springredditclone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> save(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.save(commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping("/by-user/{email}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForUser(@PathVariable String email) {
        return ResponseEntity.status(OK)
                .body(commentService.getAllCommentsForUser(email));
    }
}
