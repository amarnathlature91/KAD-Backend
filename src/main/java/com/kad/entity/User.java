package com.kad.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;
    private String email;
    private String password;
    private String role;
    private boolean isEnabled;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList;


}
