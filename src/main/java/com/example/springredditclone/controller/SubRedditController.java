package com.example.springredditclone.controller;

import com.example.springredditclone.dto.SubRedditDto;
import com.example.springredditclone.mapper.SubRedditMapper;
import com.example.springredditclone.model.SubReddit;
import com.example.springredditclone.service.SubRedditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subreddit/")
@AllArgsConstructor
public class SubRedditController {

    private final SubRedditService subRedditService;

    @PostMapping
    public ResponseEntity<SubRedditDto> createSubReddit(@RequestBody SubRedditDto subRedditDto) {
        SubReddit subReddit = subRedditService.save(subRedditDto);
        subRedditDto.setId(subReddit.getId());
        return new ResponseEntity<>(subRedditDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubRedditDto>> getAllSubReddit() {
        List<SubRedditDto> subRedditDtoList = subRedditService.getAllSubReddit()
        .stream()
                .map(SubRedditMapper.INSTANCE::mapSubredditToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(subRedditDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubRedditDto> getSubbreddit(@PathVariable Long id){
        SubReddit subReddit = subRedditService.getSubbredit(id);
        SubRedditDto subRedditDto =SubRedditMapper.INSTANCE.mapSubredditToDto(subReddit);
        return new ResponseEntity<>(subRedditDto, HttpStatus.OK);
    }
}
