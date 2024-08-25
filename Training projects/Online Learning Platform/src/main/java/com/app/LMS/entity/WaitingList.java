package com.app.LMS.entity;

import com.app.LMS.validator.ValidPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
@Entity
@Table(name = "not_available_courses_yet")
public class WaitingList {


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

}
