package com.app.LMS.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DtoStudent {

    private long id;

    private String firstName;

    private String lastName;

    private String email;
}
