package com.example.springredditclone.controller;

import com.example.springredditclone.dto.SubRedditDto;
import com.example.springredditclone.model.SubReddit;
import com.example.springredditclone.service.SubRedditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subreddit/")
@AllArgsConstructor
public class SubRedditController {

    private final SubRedditService subRedditService;

    @PostMapping
    public ResponseEntity<SubRedditDto> createSubReddit(@RequestBody SubRedditDto subRedditDto) {
        return new ResponseEntity<>(subRedditService.save(subRedditDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllSubReddit() {
        return new ResponseEntity<>(subRedditService.getAllSubReddit(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubRedditDto> getSubbreddit(@PathVariable Long id){
        return new ResponseEntity<>(subRedditService.getSubbredit(id), HttpStatus.OK);
    }
}
