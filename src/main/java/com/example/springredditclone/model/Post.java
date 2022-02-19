package com.example.springredditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Post name cannot be empty or null")
    private String name;
    @Nullable
    private String url;
    @Nullable
    private String description;
    private Integer voteCount;
    private Instant createdDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Comment> comments;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Vote> votes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subRedditId", referencedColumnName = "id")
    private SubReddit subReddit;
}
