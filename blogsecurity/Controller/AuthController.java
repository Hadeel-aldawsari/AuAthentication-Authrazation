package com.example.blogsecurity.Controller;

import com.example.blogsecurity.ApiResponse.ApiResponse;
import com.example.blogsecurity.Model.MyUser;
import com.example.blogsecurity.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog-system")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

//user

    @PostMapping("/user/register")
    public ResponseEntity Registration(@RequestBody @Valid MyUser myUser){
        authService.Registration(myUser);
        return ResponseEntity.status(200).body(new ApiResponse("user registration successfully"));
    }

    @PutMapping("/user/update")
    public ResponseEntity updateMyAccount(@AuthenticationPrincipal MyUser myUser,@RequestBody @Valid MyUser user){
        authService.updateMyAccount(myUser.getId(),user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));

    }


    //admin

    @GetMapping("/admin/get-users")
    public ResponseEntity getUsers(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(authService.getUsers(myUser.getId()));
    }


    @DeleteMapping("/admin/delete-account/{userid}")
    public ResponseEntity deleteAccount(@PathVariable Integer userid){
        authService.deleteAccount(userid);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
    }


}
