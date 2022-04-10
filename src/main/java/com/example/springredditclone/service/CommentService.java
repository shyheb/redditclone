package com.example.springredditclone.service;

import com.example.springredditclone.dto.CommentDto;
import com.example.springredditclone.model.Comment;

import java.util.List;

public interface CommentService {
    Comment save(CommentDto commentDto);
    List<Comment> getAllCommentsForPost(Long postId);
    List<Comment> getAllCommentsForUser(String email);
    boolean containsSwearWords(String comment);
}
