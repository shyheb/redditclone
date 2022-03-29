package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.SubRedditDto;
import com.example.springredditclone.model.SubReddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubRedditMapper {

    SubRedditMapper INSTANCE = Mappers.getMapper(SubRedditMapper.class);

    @Mapping(target = "numberOfPost", expression = "java(subReddit.getPosts().size())")
    SubRedditDto mapSubredditToDto(SubReddit subReddit);

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    SubReddit mapDtoToSubreddit(SubRedditDto subRedditDto);

}
