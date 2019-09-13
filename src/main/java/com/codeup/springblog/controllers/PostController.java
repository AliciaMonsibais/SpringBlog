package com.codeup.springblog.controllers;

import com.codeup.springblog.Models.User;
import com.codeup.springblog.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Repositories.PostRepository;
import com.codeup.springblog.Repositories.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    @Autowired
    private EmailService emailService;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postDao = postRepository;
        this.userDao = userRepository;
    }


    @GetMapping("/posts")
    public String allPosts(Model model) {
        Iterable<Post> posts = postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model){
        Post post = postDao.findOne(id);
        model.addAttribute("post", post);
//        String email = post.getUser().getEmail();
//        model.addAttribute("id", id);
//        model.addAttribute("title", post.getTitle());
//        model.addAttribute("body", post.getBody());
//        model.addAttribute("email", email);
        return "posts/show";
    }

    @GetMapping("/posts/search")
    public String show(@RequestParam(name = "term") String term, Model model){
        List<Post> posts = postDao.searchByTitleLike(term);
        model.addAttribute("posts", posts);
        return "posts/index";
    }


    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        Post post = postDao.findOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

@PostMapping("/posts/{id}/edit")
public String updatePost(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body, Model model) {
        Post postToBeUpdated = postDao.findOne(id);
        postToBeUpdated.setTitle(title);
        postToBeUpdated.setBody(body);
        postDao.save(postToBeUpdated);
        return "redirect:/posts/" + postToBeUpdated.getId();
        }

    @GetMapping("/posts/create")
    public String showPostForm(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post postPassedIn){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(userSession.getId());
        postPassedIn.setUser(userDB);
        Post savedPost = postDao.save(postPassedIn);
        emailService.prepareAndSend(
                savedPost,
                "Post created",
                String.format("Post with the id %d has been created", savedPost.getId())
        );
        return "redirect:/posts/" + savedPost.getId();
    }

//    @GetMapping("/posts/{id}/delete")
//    public String checkForDelete(@PathVariable int id, Model model) {
//        Post post = postDao.findOne((long) id);
//        model.addAttribute("post", post);
//        return "posts/delete";
//    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postDao.delete(id);
        return "redirect:/posts";
    }
}