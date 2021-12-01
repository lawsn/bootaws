package com.example.bootaws.web;

import com.example.bootaws.service.posts.PostsService;
import com.example.bootaws.web.dto.PostsResponseDto;
import com.example.bootaws.web.dto.PostsSaveRequestDto;
import com.example.bootaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {

        PostsResponseDto postsResponseDto = postsService.findById(id);
        log.info(postsResponseDto.toString());
        return postsResponseDto;
    }
}
