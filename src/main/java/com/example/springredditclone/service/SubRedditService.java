package com.example.springredditclone.service;

import com.example.springredditclone.dto.SubRedditDto;
import com.example.springredditclone.model.SubReddit;

import java.util.List;

public interface SubRedditService {
    SubRedditDto save(SubRedditDto subRedditDto);
    List<SubRedditDto> getAllSubReddit();
    SubRedditDto getSubbredit(Long id);
    SubReddit findByName(String name);
}
