package com.example.springredditclone.service.impl;

import com.example.springredditclone.dto.VoteDto;
import com.example.springredditclone.exceptions.FoundException;
import com.example.springredditclone.mapper.VoteMapper;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.User;
import com.example.springredditclone.model.Vote;
import com.example.springredditclone.repository.VoteRepository;
import com.example.springredditclone.service.PostService;
import com.example.springredditclone.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.springredditclone.model.VoteType.UP_VOTE;

@AllArgsConstructor
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final PostService postService;

    @Override
    @Transactional(readOnly = true)
    public void addVote(VoteDto voteDto) {
        Post post = postService.getPost(voteDto.getPostId());
        Optional<Vote> voteByPostAndUser = voteRepository.getTopByPostAndUserOrderByIdDesc(post, (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType()))
            throw new FoundException("You have already " + voteDto.getVoteType() + "'d for this post");

        if (UP_VOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        }else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        Vote vote= VoteMapper.INSTANCE.dtoToVote(voteDto,post,(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        voteRepository.save(vote);
    }
}
