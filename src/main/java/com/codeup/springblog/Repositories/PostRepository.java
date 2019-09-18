package com.codeup.springblog.Repositories;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.PostCategory;
import com.codeup.springblog.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostRepository extends CrudRepository<Post, Long> {
    Post findByTitle(String title);

    @Query("from Post p where p.title like %:term%")
    List<Post> searchByTitleLike(@Param("term") String term);
    Iterable<Post> findByUser_Id(long user_id);

//    List<Post> findAllByCats(List<PostCategory> categories);
}
