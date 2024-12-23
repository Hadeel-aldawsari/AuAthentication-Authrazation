package com.example.blogsecurity.Model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
@Data
@NoArgsConstructor
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
//
// @NotEmpty(message = "user name should not be empty")
// @Size(min=3,message = "name Length more than 3")
//  @Column(columnDefinition ="varchar(20) not null unique")
    private String username;

 // @NotEmpty(message = "password should not be empty")
 // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()]).{8,8}$", message = "Password must be between 8 and 20 characters long, and include at least one digit, one lowercase letter, one uppercase letter, and one special character (!@#&()).")
// @Column(columnDefinition ="varchar not null")
    private String password;


   // @Pattern(regexp = "ADMIN|USER",message = "role should be admin or user")
 // @Column(columnDefinition = "varchar(6) ")
    private String role;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "user")
    private Set<Blog> blogs;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }







}


