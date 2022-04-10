package com.example.springredditclone.service.impl;

import com.example.springredditclone.dto.SubRedditDto;
import com.example.springredditclone.exceptions.NotFoundException;
import com.example.springredditclone.mapper.SubRedditMapper;
import com.example.springredditclone.model.SubReddit;
import com.example.springredditclone.repository.SubRedditRepository;
import com.example.springredditclone.service.SubRedditService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SubRedditServiceImpl implements SubRedditService {

    private final SubRedditRepository subRedditRepository;

    @Override
    @Transactional
    public SubReddit save(SubRedditDto subRedditDto) {
        return subRedditRepository.save(SubRedditMapper.INSTANCE.mapDtoToSubreddit(subRedditDto));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubReddit> getAllSubReddit() {
        return subRedditRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SubReddit getSubbredit(Long id) {
        return subRedditRepository.findById(id).orElseThrow(()-> new NotFoundException("Subbreddit Not Found"));
    }

    @Override
    @Transactional(readOnly = true)
    public SubReddit findByName(String name) {
        return subRedditRepository.findByName(name).orElseThrow(()-> new NotFoundException("Subbreddit Not Found by name" + name ));
    }
}
