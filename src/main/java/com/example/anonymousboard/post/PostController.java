package com.example.anonymousboard.post;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/posts")
@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public String list(Model model) {
        List<Post> postList = postService.getAllPostList();
        model.addAttribute("postList", postList);
        return "post_list";
    }

    @PostMapping("")
//    public String list(Model model, PostForm postForm) {
    public String list(@RequestParam String title, @RequestParam String content) {
        this.postService.createPost(title, content);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String single(Model model, @PathVariable("id") Integer id) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "post";
    }

    @DeleteMapping("/{id}")
    public String singleDelete(@PathVariable("id") Integer id) {
        this.postService.deletePost(id);
        return "redirect:/posts";
    }

    @PutMapping("/{id}")
    public String singleUpdate(@PathVariable("id") Integer id, @RequestParam String title, @RequestParam String content) {
        this.postService.updatePost(id, title, content);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/{id}/form")
    public String updateForm(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("id", id);
        return "post_form";
    }

    @GetMapping("/form")
    public String writeForm() {
        return "post_form";
    }
}
