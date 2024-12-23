package com.example.blogsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@Setter
@Getter
@Data
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    //@NotEmpty(message = "title should not be empty")
   // @Size(min = 3,message = "title should be at least 3 letter ")
    //@Column(columnDefinition = "varchar")
    private String title;

    //@NotEmpty(message = "title should not be empty")
    // @Size(min = 3,message = "title should be at least 3 letter ")
    //@Column(columnDefinition = "varchar")
    private String body;

    @ManyToOne
    @JsonIgnore
    private MyUser user;
}
