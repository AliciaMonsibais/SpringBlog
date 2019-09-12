package com.codeup.springblog.controllers;

import jdk.internal.jline.extra.EditingHistory;
import org.hibernate.Session;
import com.codeup.springblog.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsById(@PathVariable long id, Model model){

        Post post = postDao.findOne((long) id);
        String email = post.getUser().getEmail();
        model.addAttribute("id", id);
        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
        model.addAttribute("email", email);
        return "posts/show";
    }

//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String newPostCreate(){
//        return "Please fill out this form.";
//    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String newPostPost(@ModelAttribute Post post){
        post.setUser(userDao.findOne(1L));
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Post post = postDao.findOne((long) id);
        model.addAttribute("id", id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

@PostMapping("/posts/{id}/edit")
public String updatePost(@PathVariable int id, @ModelAttribute Post post) {
        post.setUser(userDao.findOne(1L));
        postDao.save(post);
        return "redirect:/posts/{id}";
        }

    @GetMapping("/posts/{id}/delete")
    public String checkForDelete(@PathVariable int id, Model model) {
        Post post = postDao.findOne((long) id);
        model.addAttribute("post", post);
        return "posts/delete";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable int id) {
        postDao.delete((long) id);
        return "redirect:/posts";
    }
}