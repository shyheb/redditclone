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
    public PostDto createPost(PostDto postDto){
        SubReddit subReddit = subRedditService.findByName(postDto.getSubbreditName());
        Post post = postRepository.save(PostMapper.INSTANCE.mapDtoToPost(postDto,subReddit,
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        postDto.setId(post.getId());
        return postDto;
    }

    @Transactional(readOnly = true)
    public PostDto getPost(Long id){
       Post post = postRepository.findById(id).orElseThrow(()->new NotFoundException("Post Not Found"));
       return PostMapper.INSTANCE.mapPostToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostDto> getAllPosts(){
        List<Post> post = postRepository.findAll();
        return post
                .stream()
                .map(PostMapper.INSTANCE::mapPostToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostDto> getPostsBySubbredit(Long id){
        List<Post> post = postRepository.findBySubRedditId(id);
        return post
                .stream()
                .map(PostMapper.INSTANCE::mapPostToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostDto> getPostsByUsername(String username){
        List<Post> post = postRepository.findPostByUserName(username);
        return post
                .stream()
                .map(PostMapper.INSTANCE::mapPostToDto)
                .collect(Collectors.toList());
    }



}
