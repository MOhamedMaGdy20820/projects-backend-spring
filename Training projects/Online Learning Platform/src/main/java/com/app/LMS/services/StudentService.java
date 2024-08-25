package com.app.LMS.services;

import com.app.LMS.dto.DtoContent;
import com.app.LMS.dto.DtoCourse;
import com.app.LMS.dto.DtoReview;
import com.app.LMS.entity.Content;
import com.app.LMS.entity.Course;
import com.app.LMS.entity.Review;
import com.app.LMS.entity.Student;
import com.app.LMS.exceptions.CustomException;
import com.app.LMS.mapper.CourseMapper;
import com.app.LMS.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Log4j2
public class StudentService {

    private final StudentRepository studentRepository;

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final ContentService contentService;
    private final ReviewService reviewService;


    public Student findStudentById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new CustomException("Student not found. Please login if you want to add a course."));
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Object update(Student student) {
        return studentRepository.save(student);
    }

    public void delete(long id) {
        studentRepository.deleteById(id);
    }

    public String addCourse(long studentId, long courseId) {

        Student student = findStudentById(studentId);
        Course course = courseService.findById(courseId);

        if (student.getCourses().contains(course)) {
            return "Student is already enrolled in this course.";
        }

        student.getCourses().add(course);
        update(student);
        return "Course successfully added to the student's list.";
    }

    public List<DtoCourse> getCourses(long studentId) {
        Student student = findStudentById(studentId);
        List<Course> courses = student.getCourses();
        return courses.stream()
                .map(courseMapper::toDtoCourse) // Ensure this method exists in your mapper
                .collect(Collectors.toList());
    }

    public DtoContent getLecture(long lecNumber, long studentId, long courseId) {
        Course course = courseService.findById(courseId);
        Student student = findStudentById(studentId);

        if (!student.getCourses().contains(course)) {
            throw new CustomException("Student is not enrolled in the course.");
        }

        List<Content> contents = course.getContents();

        if (lecNumber < 0 || lecNumber > contents.size()) {
            throw new CustomException("Lecture number is out of bounds.");
        }


        Content content =contents.get((int) lecNumber - 1);
        contentService.addStudent(student,content);

        return new DtoContent(content.getLectureTitle(),content.getLectureContent());
    }



    public String makeReview (long studentId, long courseId , DtoReview dtoReview) {
        Course course = courseService.findById(courseId);
        Student student = findStudentById(studentId);

        if (!student.getCourses().contains(course)) {
            throw new CustomException("Student is not enrolled in the course.");
        }

        long numberOfContents = course.getContents().size();
        long ViewedContent = NumberOfLecturesWatchedInTheCourse(student,courseId);

        log.info(numberOfContents +"  "+ViewedContent);

        if(ViewedContent < numberOfContents){
            return "It is not allowed to write a review until after completing the course.";
        } else if (numberOfContents==0) {
            return "It is not allowed to write a review until after completing the course.";
        }

        Review review = new Review();
        review.setRating(dtoReview.getRating());
        review.setComment(dtoReview.getComment());
        review.setCourse(course);
        review.setStudent(student);

        reviewService.save(review);
        return "Review successfully added to the student's list.";
    }

    public double WatchingRate(long studentId, long courseId) {

        Course course = courseService.findById(courseId);
        Student student = findStudentById(studentId);

        if (!student.getCourses().contains(course)) {
            throw new CustomException("Student is not enrolled in the course.");
        }


        double numberOfContents = course.getContents().size();
        if (numberOfContents==0) {
            return 0 ;
        }
        return (NumberOfLecturesWatchedInTheCourse(student,courseId)/numberOfContents) * 100 ;
    }

    public long NumberOfLecturesWatchedInTheCourse(Student student, long courseId){
        return   student.getContents().stream()
                .filter(content -> content.getCourse().getId() == courseId) // تصفية بناءً على courseId
                .distinct() // تجنب التكرارات
                .count(); // عدّ العناصر
    }


    public List<DtoCourse> getAllCourses() {
        List<Course> courses = courseService.findAll();
        return courses.stream()
                .map(courseMapper::toDtoCourse) // Ensure this method exists in your mapper
                .collect(Collectors.toList());
    }


}
