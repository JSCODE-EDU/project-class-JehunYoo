package com.example.anonymousboard.post;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시물")
@Getter
@Setter // TODO @Builer로 수정하기
@Entity
public class Post {

    @Schema(description = "고유 번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "제목")
    @NotBlank(message = "Title must not be blank.")
    @Size(min = 1, max = 25)
    @Column(length = 25)
    private String title;

    @Schema(description = "내용")
    @NotEmpty(message = "Content must not be empty.")
    @Size(min = 1, max = 1000)
    @Column(length = 1000)
    private String content;

    @Schema(description = "생성 일시")
    private LocalDateTime createdAt;
}
