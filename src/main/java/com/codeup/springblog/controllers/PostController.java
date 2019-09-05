package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public void allPosts() {
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public void postsById(){
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public void newPostCreate(){
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public void newPostPost(){
    }
}
