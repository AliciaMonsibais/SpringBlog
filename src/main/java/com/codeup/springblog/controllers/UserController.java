package com.codeup.springblog.controllers;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Repositories.PostRepository;
import com.codeup.springblog.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import com.codeup.springblog.Models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private PostRepository postDao;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.postDao = postDao;
    }

    @GetMapping("/register")
    public String showSignUp(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        User userSession= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterable<Post> posts= postDao.findByUser(userSession);
        model.addAttribute("posts",posts);
        model.addAttribute("user",userSession);
        return "users/profile";
    }
}