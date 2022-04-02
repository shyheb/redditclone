package com.example.springredditclone.service;

import com.example.springredditclone.dto.CommentDto;
import com.example.springredditclone.model.Comment;

import java.util.List;

public interface CommentService {
    CommentDto save(CommentDto commentDto);
    List<CommentDto> getAllCommentsForPost(Long postId);
    List<CommentDto> getAllCommentsForUser(String email);
    boolean containsSwearWords(String comment);
}
