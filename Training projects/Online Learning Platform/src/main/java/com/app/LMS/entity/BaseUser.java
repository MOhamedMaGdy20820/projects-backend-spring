package com.app.LMS.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass // not normal class not entity

public abstract class BaseUser<ID> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private ID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
