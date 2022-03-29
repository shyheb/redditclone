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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubRedditDtoServiceImpl implements SubRedditService {

    private final SubRedditRepository subRedditRepository;

    @Override
    @Transactional
    public SubRedditDto save(SubRedditDto subRedditDto) {
        SubReddit subReddit = subRedditRepository.save(SubRedditMapper.INSTANCE.mapDtoToSubreddit(subRedditDto));
        subRedditDto.setId(subReddit.getId());
        return subRedditDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<SubRedditDto> getAllSubReddit() {
        return subRedditRepository.findAll()
                .stream()
                .map(SubRedditMapper.INSTANCE::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubRedditDto getSubbredit(Long id) {
        SubReddit subReddit = subRedditRepository.findById(id).orElseThrow(()-> new NotFoundException("Subbreddit Not Found"));
        return SubRedditMapper.INSTANCE.mapSubredditToDto(subReddit);
    }

    @Override
    public SubReddit findByName(String name) {
        return subRedditRepository.findByName(name).orElseThrow(()-> new NotFoundException("Subbreddit Not Found"));
    }
}
