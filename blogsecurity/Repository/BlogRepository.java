package com.example.blogsecurity.Repository;

import com.example.blogsecurity.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Blog findBlogById(Integer id);
    Blog findBlogByTitle(String title);
    List<Blog> findBlogsByUserId(Integer id);


}
