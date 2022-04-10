package com.example.springredditclone.dto;

import com.example.springredditclone.model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class VoteDto {
    private VoteType voteType;
    private Long postId;
}
