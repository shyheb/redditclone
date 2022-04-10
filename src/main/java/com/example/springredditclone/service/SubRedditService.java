package com.example.springredditclone.service;

import com.example.springredditclone.dto.SubRedditDto;
import com.example.springredditclone.model.SubReddit;

import java.util.List;

public interface SubRedditService {
    SubReddit save(SubRedditDto subRedditDto);
    List<SubReddit> getAllSubReddit();
    SubReddit getSubbredit(Long id);
    SubReddit findByName(String name);
}
