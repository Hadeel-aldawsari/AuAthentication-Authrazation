package com.example.blogsecurity.Service;

import com.example.blogsecurity.ApiResponse.ApiException;
import com.example.blogsecurity.Model.MyUser;
import com.example.blogsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
//User
    public void Registration(MyUser myUser){
        myUser.setRole("USER");
        String hashPass=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashPass);
        authRepository.save(myUser);
    }

    public void updateMyAccount(Integer userid, MyUser myUser){
        MyUser oldUser=authRepository.findMyUserById(userid);
        if(oldUser==null)throw new ApiException("user id not found");
        String hashPass=new BCryptPasswordEncoder().encode(myUser.getPassword());
        oldUser.setPassword(hashPass);
        oldUser.setUsername(myUser.getUsername());
        authRepository.save(oldUser);
    }

//Admin
    public List<MyUser> getUsers(Integer userid){
        MyUser myUser=authRepository.findMyUserById(userid);
        if(myUser==null)throw new ApiException("admin not found");

        return authRepository.findMyUserByRole("USER");
    }

    public void deleteAccount(Integer userid){
        MyUser myUser=authRepository.findMyUserById(userid);
        if(myUser==null)throw new ApiException("user id not found");

        authRepository.delete(myUser);
    }


}
