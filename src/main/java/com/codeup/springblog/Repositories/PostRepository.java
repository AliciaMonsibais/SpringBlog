package com.codeup.springblog.Repositories;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostRepository extends CrudRepository<Post, Long> {
    Post findByTitle(String title);
    Iterable<Post> findByUser(User user);

    @Query("from Post where title like %:term%")
    List<Post> searchByTitleLike(@Param("term") String term);
}
