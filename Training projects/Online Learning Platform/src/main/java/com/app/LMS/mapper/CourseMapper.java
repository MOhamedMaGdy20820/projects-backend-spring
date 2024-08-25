package com.app.LMS.mapper;

import com.app.LMS.dto.DtoCourse;
import com.app.LMS.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public DtoCourse toDtoCourse(Course course) {
        return new DtoCourse(course.getTitle(), course.getDescription(), course.getCategory());
    }
}