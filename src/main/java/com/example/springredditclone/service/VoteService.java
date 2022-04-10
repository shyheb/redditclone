package com.example.springredditclone.service;

import com.example.springredditclone.dto.VoteDto;

import java.net.URI;

public interface VoteService {
    void addVote(VoteDto voteDto);
}
