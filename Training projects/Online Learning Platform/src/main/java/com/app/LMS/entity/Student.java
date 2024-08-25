package com.app.LMS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
public class Student extends BaseUser <Long> {

    public Student() {
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    ,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns =@JoinColumn(name = "student_id"),
            inverseJoinColumns =@JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private List<Course> courses;


    @OneToMany(mappedBy = "student" , cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    ,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "content_student",
            joinColumns =@JoinColumn(name = "student_id"),
            inverseJoinColumns =@JoinColumn(name = "content_id")
    )
    private List<Content> contents;
}
