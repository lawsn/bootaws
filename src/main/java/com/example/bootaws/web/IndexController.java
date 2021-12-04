package com.example.bootaws.web;

import com.example.bootaws.config.auth.LoginUser;
import com.example.bootaws.config.auth.dto.SessionUser;
import com.example.bootaws.domain.user.User;
import com.example.bootaws.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findById(1L));
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
}
