package com.app.LMS.services;

import com.app.LMS.dto.DtoContent;
import com.app.LMS.dto.DtoCourse;
import com.app.LMS.entity.Content;
import com.app.LMS.entity.Course;
import com.app.LMS.exceptions.CustomException;
import com.app.LMS.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;


    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course findById(Long id) {
        return  courseRepository.findById(id).orElseThrow(() ->
                new CustomException("Course not found. Please select a valid course."));
    }

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public String addContent(List<DtoContent> dtoContents, long instructorId, long courseId) {

        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            if(course.get().getInstructor().getId() == instructorId) {

                course.get().setContents(convertToContentList(dtoContents));
                courseRepository.save(course.get());

            }else return "Is it not your course";
        }else return "course not found";
        return "Content added successfully";
    }


    public List<Content> convertToContentList(List<DtoContent> dtoContentList) {
        return dtoContentList.stream()
                .map(dtoContent -> {
                    Content content = new Content();
                    content.setLectureTitle(dtoContent.getLectureTitle());
                    content.setLectureContent(dtoContent.getLectureContent());
                    return content;
                })
                .collect(Collectors.toList());
    }

    public void deleteCourse(long courseId) {
        courseRepository.deleteById(courseId);
    }

    public void editCourse(long id,DtoCourse dtoCourse) {
        Course course = findById(id);
        course.setTitle(dtoCourse.getTitle());
        course.setCategory(dtoCourse.getCategory());
        course.setDescription(dtoCourse.getDescription());
        courseRepository.save(course);
    }
}
