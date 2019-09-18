//package com.codeup.springblog.controllers;
//
//import com.codeup.springblog.Repositories.CategoryRepository;
//import com.codeup.springblog.Repositories.PostRepository;
//import com.codeup.springblog.Models.PostCategory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class CategoryController {
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    PostRepository postRepository;
//
////    @GetMapping("/posts/categories")
////    @ResponseBody
////    public Iterable<PostCategory> getCategories(){
////        List<PostCategory> categoryList = CategoryRepository.findAll();
////        return categoryList;
////    }
//
//    @GetMapping("/posts/categories/{id}")
//    public String showPostsByCat(@PathVariable long id, Model model) {
//        List<PostCategory> categories = new ArrayList<>();
//        categories.add(categoryRepository.getOne(id));
//        model.addAttribute("body", postRepository.findAllByCats(categories));
//        model.addAttribute("title", "Posts by category: " + categories.get(0).getName());
//        return "posts/categories";
//    }
//}
