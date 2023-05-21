package com.example.anonymousboard.post;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Optional<Post> getPost(Integer id) {
        Optional<Post> optionalPost = this.postRepository.findById(id);
        return optionalPost;
    }

    public List<Post> getAllPostList() {
        List<Post> all = this.postRepository.findTop100By(Sort.by("createdAt").descending());
        return all;
    }

    public Post createPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        this.postRepository.save(post);
        return post;
    }

    public Optional<Post> deletePost(Integer id) {
        Optional<Post> optionalPost = this.postRepository.findById(id);
        if(optionalPost.isPresent()) {
            this.postRepository.deleteById(id);
        }
        return optionalPost;
    }

    public Optional<Post> updatePost(Integer id, String title, String content) {
        Optional<Post> optionalPost = this.postRepository.findById(id);
        if(optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(title);
            post.setContent(content);
            this.postRepository.save(post);
        }
        return optionalPost;
    }

    public List<Post> searchPostByTitle(String title) {
        List<Post> posts = this.postRepository.findTop100ByTitleLike("%" + title + "%", Sort.by("createdAt").descending());
        return posts;
    }
}
