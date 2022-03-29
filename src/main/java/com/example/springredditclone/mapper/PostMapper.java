package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.PostDto;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.SubReddit;
import com.example.springredditclone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "postDto.postName")
    @Mapping(target = "url", source = "postDto.url")
    @Mapping(target = "description", source = "postDto.description")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "subReddit", source = "subReddit")
    Post mapDtoToPost(PostDto postDto, SubReddit subReddit, User user);
}
