package com.example.anonymousboard;

import com.example.anonymousboard.post.Post;
import com.example.anonymousboard.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnonymousBoardApplicationTests {

    @Autowired
    private PostRepository postRepository;

    @Test
    void testJpa() {
        for(int i=0; i<3; i++) {
            Post p = new Post();
            p.setTitle(String.format("This is title %d", i));
            p.setContent(String.format("This is content %d", i));
            this.postRepository.save(p);
        }
    }

    @Test
    void contextLoads() {

    }

}
