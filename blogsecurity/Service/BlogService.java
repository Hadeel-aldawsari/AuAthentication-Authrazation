package com.example.blogsecurity.Service;

import com.example.blogsecurity.ApiResponse.ApiException;
import com.example.blogsecurity.Model.Blog;
import com.example.blogsecurity.Model.MyUser;
import com.example.blogsecurity.Repository.AuthRepository;
import com.example.blogsecurity.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;
     //User
    public void addBlog(Integer userid, Blog blog){
        MyUser user=authRepository.findMyUserById(userid);
        if(user ==null)throw new ApiException("user not found");

        blog.setUser(user);
        blogRepository.save(blog);
    }


    public void updateMyBlog(Integer userid, Integer blogid,Blog blog){
        MyUser user=authRepository.findMyUserById(userid);
        if(user==null)throw new ApiException("user not found");

        Blog oldBlog=blogRepository.findBlogById(blogid);
        if(oldBlog==null)throw new ApiException("blog not found");

        if(!oldBlog.getUser().getId().equals(userid))throw new ApiException("Unauthorized to update this blog");

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);


    }

    public  void deleteMyBlog(Integer userid, Integer blogid){
        MyUser user=authRepository.findMyUserById(userid);
        if(user==null)throw new ApiException("user not found");

        Blog blog=blogRepository.findBlogById(blogid);
        if(blog==null)throw new ApiException("blog not found");
        if(!blog.getUser().getId().equals(userid))throw new ApiException("Unauthorized to delete this blog");

        blogRepository.delete(blog);
    }

    public Blog getBlogByTitle(String title){
        Blog blog=blogRepository.findBlogByTitle(title);
        if(blog==null)throw new ApiException("no blog found with this id");
        return blog;
    }

    public Blog getBlogById(Integer blogid){
        Blog blog=blogRepository.findBlogById(blogid);
        if(blog==null)throw new ApiException("no blog found with this id");
        return blog;
    }

    public List<Blog> getMyBlogs(Integer userid){
        List<Blog> blogs=blogRepository.findBlogsByUserId(userid);
        if(blogs==null ||  blogs.isEmpty())throw new ApiException("no blogs found");
        return blogs;
    }

    //Admin
    public List<Blog> getAllBlogs(){
        if(blogRepository.findAll().isEmpty())throw new ApiException("there is no blog posted");
        return blogRepository.findAll();
    }



}
