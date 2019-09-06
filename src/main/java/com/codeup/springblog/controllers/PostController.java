package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String allPosts() {
        return "Here are all the posts!";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postsById(@PathVariable long id){
        return "Here is post #" + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String newPostCreate(){
        return "Please fill out this form.";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String newPostPost(){
        return "Here's the new post!";
    }
}
