package com.app.LMS.services;

import com.app.LMS.dto.DtoStudent;
import com.app.LMS.entity.Course;
import com.app.LMS.entity.Instructor;
import com.app.LMS.entity.Student;
import com.app.LMS.exceptions.CustomException;
import com.app.LMS.repository.InstructorRepository;
import com.app.LMS.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseService courseService;
    private final StudentService studentService;

    public Instructor findInstructorById(long id) {
       return instructorRepository.findById(id).orElseThrow(()->new CustomException("instructor not found"));

    }
    public List<DtoStudent> getStudentsInCourse(long instructorId, long courseId) {
        Course course = courseService.findById(courseId);

        if(course.getInstructor().getId() != instructorId) {
           throw new CustomException("it is not your course");
        }
        return convertToDto(course.getStudents());
    }

    public static List<DtoStudent> convertToDto(List<Student> students) {
        return students.stream()
                .map(student -> {
                    DtoStudent dtoStudent =
                            new DtoStudent(student.getId(),student.getFirstName(),student.getLastName(),student.getEmail());
                    return dtoStudent;
                })
                .collect(Collectors.toList());
    }

    public void deleteStudentFromCourse(long instructorId, long courseId, long studentId) {
        Course course = courseService.findById(courseId);
        if(course.getInstructor().getId() != instructorId) {
            throw new CustomException("it is not your course");
        }
        Student student =studentService.findStudentById(studentId);
        if(!(course.getStudents().contains(student))){
            throw new CustomException("it is not your student");
        }
        course.getStudents().remove(student);
        courseService.save(course);

    }
}
