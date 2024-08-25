package com.app.LMS.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String LectureTitle;

    private String LectureContent;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE
                    ,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "content_student",
            joinColumns =@JoinColumn(name = "content_id"),
            inverseJoinColumns =@JoinColumn(name = "student_id")
    )
    @JsonIgnore
    private List<Student> students;


    public void setStudent(Student student) {
        students.add(student);
    }


}
