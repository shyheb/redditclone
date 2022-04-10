package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.PostDto;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.SubReddit;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.CommentRepository;
import com.example.springredditclone.repository.VoteRepository;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "postDto.postName")
    @Mapping(target = "url", source = "postDto.url")
    @Mapping(target = "description", source = "postDto.description")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "subReddit", source = "subReddit")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post mapDtoToPost(PostDto postDto, SubReddit subReddit, User user);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "subbreditName", source = "subReddit.name")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "postName", source = "name")
    public abstract PostResponse mapToDto(Post post);


    Integer commentCount(Post post) {
        return commentRepository.findByPostId(post.getId()).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
}
