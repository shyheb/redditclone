package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.VoteDto;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.User;
import com.example.springredditclone.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteMapper {

    VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "voteType", source = "voteDto.voteType")
    @Mapping(target = "user", source = "currentUser")
    @Mapping(target = "post", source = "post")
    Vote dtoToVote (VoteDto voteDto, Post post, User currentUser);
}
