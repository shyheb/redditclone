package com.example.springredditclone.repository;

import com.example.springredditclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //test query
    @Query(value = "select p from Post p where p.subReddit.id = ?1 ")
    List<Post> findBySubRedditId(Long subRedditId);

    List<Post> findPostByUserName(String username);
}
