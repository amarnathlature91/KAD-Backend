package com.kad.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    private String jobTitle;

    private String location;

    private String salary;

    private String experience;

    @Column(length = 3000)
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateLastModified;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
