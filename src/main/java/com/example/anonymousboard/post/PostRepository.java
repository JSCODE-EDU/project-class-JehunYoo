package com.example.anonymousboard.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findTop100ByTitleLike(String title, Sort sort);
    List<Post> findTop100By(Sort sort);
}
