package com.example.springredditclone.repository;

import com.example.springredditclone.model.SubReddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubRedditRepository extends JpaRepository<SubReddit, Long> {
}
