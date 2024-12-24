package com.example.blogsecurity.Controller;


import com.example.blogsecurity.ApiResponse.ApiResponse;
import com.example.blogsecurity.Model.Blog;
import com.example.blogsecurity.Model.MyUser;
import com.example.blogsecurity.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog-system/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

     //User
    @PostMapping("/user/add-blog")
    public ResponseEntity addBlog(@AuthenticationPrincipal MyUser myuser, @RequestBody @Valid Blog blog){
        blogService.addBlog(myuser.getId(),blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog added successfully"));
    }


    @PutMapping("/user/update-blog/{blogid}")
    public ResponseEntity updateMyBlog(@AuthenticationPrincipal MyUser myuser,@PathVariable Integer blogid,@RequestBody @Valid Blog blog){
        blogService.updateMyBlog(myuser.getId(),blogid,blog);
        return ResponseEntity.status(200).body(new ApiResponse("blog updated successfully"));
    }

    @DeleteMapping("/user/delete-blog/{blogid}")
    public ResponseEntity deleteMyBlog(@AuthenticationPrincipal MyUser myuser, @PathVariable Integer blogid){
        blogService.deleteMyBlog(myuser.getId(),blogid);
        return ResponseEntity.status(200).body(new ApiResponse("blog deleted successfully"));

    }

    @GetMapping("/user/get-blog-by-title/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }


    @GetMapping("/user/get-my-blogs")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal MyUser myuser){
        return ResponseEntity.status(200).body(blogService.getMyBlogs(myuser.getId()));
    }

    @GetMapping("/user/get-my-blog-by-id/{blogid}")
    public ResponseEntity getBlogById(@PathVariable Integer blogid){
        return ResponseEntity.status(200).body(blogService.getBlogById(blogid));
    }


    //Admin
    @GetMapping("/admin/get-blogs")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

   

}


