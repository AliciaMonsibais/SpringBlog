package com.codeup.springblog.controllers;

import jdk.internal.jline.extra.EditingHistory;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import com.codeup.springblog.Models.Post;

import com.codeup.springblog.Models.User;
import com.codeup.springblog.Repositories.PostRepository;
import com.codeup.springblog.Repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

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

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Post post = postDao.findOne((long) id);
        model.addAttribute("id", id);
        model.addAttribute("post", post);
        return null;
    }

@PostMapping("/posts/{id}/edit")
public String updatePost(@PathVariable int id, @ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts/{id}";
        }

    @GetMapping("/posts/{id}/delete")
    public String checkForDelete(@PathVariable int id, Model model) {
        Post post = postDao.findOne((long) id);
        model.addAttribute("post", post);
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable int id) {
        postDao.delete((long) id);
        return "redirect:/posts";
    }
}