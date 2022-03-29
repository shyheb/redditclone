package com.example.springredditclone.repository;

import com.example.springredditclone.model.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubRedditRepository extends JpaRepository<SubReddit, Long> {
    Optional<SubReddit> findByName(String name);
}
