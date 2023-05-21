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


@Getter
@Setter // TODO @Builer로 수정하기
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title must not be blank.")
    @Size(min = 1, max = 25)
    @Column(length = 25)
    private String title;

    @NotEmpty(message = "Content must not be empty.")
    @Size(min = 1, max = 1000)
    @Column(length = 1000)
    private String content;

    private LocalDateTime createdAt;
}
