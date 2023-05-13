package com.example.anonymousboard.post;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public List<Post> list() {
        List<Post> postList = postService.getAllPostList();
        return postList;
    }

    @PostMapping("")
    public Post list(@RequestParam String title, @RequestParam String content) {
        Post post = this.postService.createPost(title, content);
        return post;
    }

    @GetMapping("/{id}")
    public Post single(@PathVariable("id") Integer id) {
        Post post = postService.getPost(id);
        return post;
    }

    @DeleteMapping("/{id}")
    public void singleDelete(@PathVariable("id") Integer id) {
        this.postService.deletePost(id);
        return;
    }

    @PutMapping("/{id}")
    public Post singleUpdate(@PathVariable("id") Integer id, @RequestParam String title, @RequestParam String content) {
        Post post = this.postService.updatePost(id, title, content);
        return post;
    }
}
