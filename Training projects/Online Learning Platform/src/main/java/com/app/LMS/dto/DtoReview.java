package com.app.LMS.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DtoReview {
    private long id;
    private int rating;
    private String comment;
}
