package com.example.bootaws.service.posts;

import com.example.bootaws.domain.posts.Posts;
import com.example.bootaws.domain.posts.PostsRepository;
import com.example.bootaws.web.dto.PostsResponseDto;
import com.example.bootaws.web.dto.PostsSaveRequestDto;
import com.example.bootaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No found user data. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("no found user data. id=" + id));

        return new PostsResponseDto(entity);
    }
}
