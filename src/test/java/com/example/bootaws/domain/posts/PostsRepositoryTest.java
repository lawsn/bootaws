package com.example.bootaws.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void getAfterSavePosts() {
        String title = "Title of posts by Test";
        String content = "Content of posts by Test";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("bsoh@glninternational.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void registBaseTimeEntity() {
        LocalDateTime now = LocalDateTime.of(2021,11,28,0,0,0);
        postsRepository.save(Posts.builder()
                .title("n title")
                .content("date content")
                .author("doo vinc")
                .build());
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> createDate="+posts.getCreatedTime()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedTime()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
