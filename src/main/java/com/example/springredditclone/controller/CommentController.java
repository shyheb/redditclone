package com.example.springredditclone.controller;

import com.example.springredditclone.dto.CommentDto;
import com.example.springredditclone.mapper.CommentMapper;
import com.example.springredditclone.model.Comment;
import com.example.springredditclone.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> save(@RequestBody CommentDto commentDto) {
        Comment comment = commentService.save(commentDto);
        commentDto.setId(comment.getId());
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable Long postId) {
        List<CommentDto> commentDtoList = commentService.getAllCommentsForPost(postId)
                .stream()
                .map(CommentMapper.INSTANCE::mapCommentToDto).collect(toList());
        return ResponseEntity.status(OK)
                .body(commentDtoList);
    }

    @GetMapping("/by-user/{email}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForUser(@PathVariable String email) {
        List<CommentDto> commentDtoList = commentService.getAllCommentsForUser(email)
                .stream()
                .map(CommentMapper.INSTANCE::mapCommentToDto)
                .collect(toList());
        return ResponseEntity.status(OK)
                .body(commentDtoList);
    }
}
