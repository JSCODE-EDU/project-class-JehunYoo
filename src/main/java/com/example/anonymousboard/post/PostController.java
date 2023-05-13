package com.example.anonymousboard.post;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<List<Post>> list() {
        List<Post> postList = postService.getAllPostList();
        return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Post> list(@RequestParam String title, @RequestParam String content) {
        Post post = this.postService.createPost(title, content);
        return new ResponseEntity<Post>(post, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> single(@PathVariable("id") Integer id) {
        Post post = postService.getPost(id);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> singleDelete(@PathVariable("id") Integer id) {
        this.postService.deletePost(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> singleUpdate(@PathVariable("id") Integer id, @RequestParam String title, @RequestParam String content) {
        Post post = this.postService.updatePost(id, title, content);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
}
