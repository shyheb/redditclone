package com.example.springredditclone.service.impl;

import com.example.springredditclone.dto.CommentDto;
import com.example.springredditclone.exceptions.NotFoundException;
import com.example.springredditclone.exceptions.WordException;
import com.example.springredditclone.mapper.CommentMapper;
import com.example.springredditclone.model.Comment;
import com.example.springredditclone.model.NotificationEmail;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.CommentRepository;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.UserRepository;
import com.example.springredditclone.service.CommentService;
import com.example.springredditclone.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MailService mailService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Comment save(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(() -> new NotFoundException("Post Not Found"));
        Comment comment = commentRepository.save(CommentMapper.INSTANCE.mapDtoToComment(
                commentDto,
                post,
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        );


        String message = post.getUser().getUsername() + " posted a comment on your post.";
        sendCommentNotification(message, post.getUser());

        return comment;
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendEmail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    @Transactional(readOnly = true)
    public List<Comment> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("POST NOT FOUND"));
        return commentRepository.findByPostId(post.getId());
    }

    @Transactional(readOnly = true)
    public List<Comment> getAllCommentsForUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User name not found exception"));
        return commentRepository.findByUserEmail(email);
    }

    public boolean containsSwearWords(String comment) {
        if (comment.contains("shit")) {
            throw new WordException("Comments contains unacceptable language");
        }
        return false;
    }


}
