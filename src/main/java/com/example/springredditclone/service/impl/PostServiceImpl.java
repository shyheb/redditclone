package com.example.springredditclone.service.impl;

import com.example.springredditclone.dto.PostDto;
import com.example.springredditclone.dto.SubRedditDto;
import com.example.springredditclone.mapper.PostMapper;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.SubReddit;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.service.AuthService;
import com.example.springredditclone.service.PostService;
import com.example.springredditclone.service.SubRedditService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@AllArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final SubRedditService subRedditService;

    public PostDto createPost(PostDto postDto){
        SubReddit subReddit = subRedditService.findByName(postDto.getSubbreditName());
        Post post = postRepository.save(PostMapper.INSTANCE.mapDtoToPost(postDto,subReddit,
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        postDto.setId(post.getId());
        return postDto;
    }


}
