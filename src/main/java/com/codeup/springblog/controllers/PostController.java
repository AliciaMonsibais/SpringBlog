package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import com.codeup.springblog.Models.Post;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String allPosts(Model model) {
        Post [] posts = new Post[2];
        posts[0] = new Post("Test", "Testing 123.");
        posts[1] = new Post("Test2", "Testing 456");
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsById(@PathVariable long id, Model model){

        Post post = new Post("Test2", "Testing 456");
        String title = post.getTitle();
        String body = post.getBody();
        model.addAttribute("title", title);
        model.addAttribute("body", body);
        return "posts/show";
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
