package com.example.anonymousboard.post;

import java.util.Optional;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Post getPost(Integer id) {
        Optional<Post> optionalPost = this.postRepository.findById(id);
        if(optionalPost.isPresent()) {
            return optionalPost.get();
        } else {
            throw new RuntimeException("Post Not Found");
        }
    }

    public List<Post> getAllPostList() {
        List<Post> all = this.postRepository.findAll();
        return all;
    }

    public Post createPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        this.postRepository.save(post);
        return post;
    }

    public void deletePost(Integer id) {
        this.postRepository.deleteById(id);
    }

    public Post updatePost(Integer id, String title, String content) {
        Post post = getPost(id);
        post.setTitle(title);
        post.setContent(content);
        this.postRepository.save(post);
        return post;
    }
}
