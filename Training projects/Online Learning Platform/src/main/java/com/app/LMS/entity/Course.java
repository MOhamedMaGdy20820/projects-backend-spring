package com.app.LMS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title ;

    private String description;

    private String category;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private Instructor instructor;


    @ManyToMany(fetch = FetchType.LAZY,
                    cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns =@JoinColumn(name = "course_id"),
            inverseJoinColumns =@JoinColumn(name = "student_id")
    )
    @JsonIgnore
    private List<Student> students;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "Course_id")
    @JsonIgnore
    private List<Content> contents ;


    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "Course_id")
    @JsonIgnore
    private List<Review> reviews ;


}
