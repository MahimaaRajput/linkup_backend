package com.LinkUp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String caption;
    private String image;
    private String video;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties({"password", "followers", "following", "savedPost", "liked"})
    private List<User> liked = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    private User user;

    private LocalDateTime createdAt;


}

