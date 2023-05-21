package com.example.anonymousboard.post;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "Post", description = "Post API")
@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
@Validated
public class PostController {

    private final PostService postService;

    @GetMapping("")
    @Operation(summary = "최근 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "최근 게시글 조회 성공")
    })
    public ResponseEntity<List<Post>> list() {
        List<Post> postList = postService.getAllPostList();
        return new ResponseEntity<List<Post>>(postList, HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(summary = "게시글 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "게시글 등록 성공")
    })
    public ResponseEntity<Post> list(@Parameter(name = "post", description = "Post entity", in = ParameterIn.HEADER) @Valid @RequestBody Post p) {
        Post post = this.postService.createPost(p.getTitle(), p.getContent());
        return new ResponseEntity<Post>(post, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 게시글 조회 성공"),
            @ApiResponse(responseCode = "404", description = "특정 게시글 조회 실패")
    })
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
    @Operation(summary = "특정 게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "특정 게시글 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "특정 게시글 삭제 실패")
    })
    public ResponseEntity<Void> singleDelete(@PathVariable("id") Integer id) {
        Optional<Post> optionalPost = this.postService.deletePost(id);
        if(optionalPost.isPresent()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "특정 게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "특정 게시글 수정 성공"),
            @ApiResponse(responseCode = "404", description = "특정 게시글 수정 실패")
    })
    public ResponseEntity<Post> singleUpdate(@PathVariable("id") Integer id, @Valid @RequestBody Post p) {
        Optional<Post> optionalPost = this.postService.updatePost(id, p.getTitle(), p.getContent());
        if(optionalPost.isPresent()) {
            return new ResponseEntity<Post>(optionalPost.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search") // TODO: /search -> REST API?
    @Operation(summary = "게시글 제목 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 제목 검색 성공")
    })
    public ResponseEntity<List<Post>> searchByTitle(@NotBlank(message = "Title must not be blank.") @RequestParam("title") String title) {
        List<Post> posts = this.postService.searchPostByTitle(title);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
}
