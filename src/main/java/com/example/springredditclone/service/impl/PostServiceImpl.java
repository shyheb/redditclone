package com.example.springredditclone.service.impl;

import com.example.springredditclone.dto.PostDto;
import com.example.springredditclone.dto.SubRedditDto;
import com.example.springredditclone.exceptions.NotFoundException;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final SubRedditService subRedditService;

    @Transactional
    public Post createPost(PostDto postDto){
        SubReddit subReddit = subRedditService.findByName(postDto.getSubbreditName());
        return postRepository.save(PostMapper.INSTANCE.mapDtoToPost(postDto,subReddit,
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @Transactional(readOnly = true)
    public Post getPost(Long id){
        return postRepository.findById(id).orElseThrow(()->new NotFoundException("Post Not Found"));
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Post> getPostsBySubbredit(Long id){
        return postRepository.findBySubRedditId(id);
    }

    @Transactional(readOnly = true)
    public List<Post> getPostsByUsername(String username){
        return postRepository.findPostByUserName(username);
    }



}
