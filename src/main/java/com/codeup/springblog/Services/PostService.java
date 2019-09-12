package com.codeup.springblog.Services;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Repositories.PostRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private final PostRepository postDao;

    public PostService(PostRepository postDao) {
        this.postDao = postDao;
    }

    public List<Post> findAll() {
        return (List<Post>) postDao.findAll();
    }

    public Post findOne(long id) {
        return postDao.findOne(id);
    }

//    public Post findbyId(){
//        return postDao.findbyID();
//    }

    public void save(Post post) {
        postDao.save(post);
    }

    public void delete(Post post) {
        postDao.delete(post);
    }

    private void deleteByID(long id){
        postDao.delete(id);
    }
}
