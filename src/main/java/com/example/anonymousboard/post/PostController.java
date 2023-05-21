package com.example.anonymousboard.post;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;

@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
@Validated
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<List<Post>> list() {
        List<Post> postList = postService.getAllPostList();
        return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Post> list(@Valid @RequestBody Post p) {
        Post post = this.postService.createPost(p.getTitle(), p.getContent());
        return new ResponseEntity<Post>(post, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> single(@PathVariable("id") Integer id) {
        Optional<Post> optionalPost = postService.getPost(id);
        if(optionalPost.isPresent()) {
            Post post = optionalPost.get();
            return new ResponseEntity<Post>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> singleDelete(@PathVariable("id") Integer id) {
        Optional<Post> optionalPost = this.postService.deletePost(id);
        if(optionalPost.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> singleUpdate(@PathVariable("id") Integer id, @Valid @RequestBody Post p) {
        Optional<Post> optionalPost = this.postService.updatePost(id, p.getTitle(), p.getContent());
        if(optionalPost.isPresent()) {
            return new ResponseEntity<Post>(optionalPost.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search") // TODO: /search -> REST API?
    public ResponseEntity<List<Post>> searchByTitle(@NotBlank(message = "Title must not be blank.") @RequestParam("title") String title) {
        List<Post> posts = this.postService.searchPostByTitle(title);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
}
