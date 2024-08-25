package com.app.LMS.controllers;

import com.app.LMS.dto.DtoContent;
import com.app.LMS.dto.DtoCourse;
import com.app.LMS.dto.DtoReview;
import com.app.LMS.entity.Content;
import com.app.LMS.entity.Course;
import com.app.LMS.entity.Student;
import com.app.LMS.entity.WaitingList;
import com.app.LMS.services.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RestController
@RequestMapping("/Admin")
@AllArgsConstructor
public class AdminController {

    private final WaitingListService waitingListService;
    private final StudentService studentService;
    private final ReviewService reviewService;
    private final CourseService courseService;
    private final ContentService contentService;


    @GetMapping("/students")
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @PutMapping("/editStudent")
    public ResponseEntity<?> update(@RequestBody @Valid Student student) {
        return ResponseEntity.ok(studentService.update(student));
    }
    @DeleteMapping("/deleteStudent/{id}")
    public void update(@PathVariable long id) {
         studentService.delete(id);
    }

    @GetMapping("/waitingList")
    public ResponseEntity<List<WaitingList>> findAllCourses() {
        return ResponseEntity.ok(waitingListService.getAll());
    }

    @PostMapping("/acceptCourse/{id}")
    public ResponseEntity<String> acceptCourse (@PathVariable long id) {
        return ResponseEntity.status(OK).body(waitingListService.acceptCourse(id));
    }

    @DeleteMapping("/rejectCourse/{id}")
    public void rejectCourse (@PathVariable long id) {
        waitingListService.rejectCourse(id);
    }

    @GetMapping("/getAllReviews")
    public ResponseEntity<List<DtoReview>> getAllReviews () {
        return  ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviews());
    }

    @DeleteMapping("/deleteReview")
    public void deleteReview (@RequestParam("reviewId") long reviewId) {
          reviewService.deleteReview(reviewId);
    }

    @DeleteMapping("/deleteCourse")
    public void deleteStudentFromCourse (@RequestParam("courseId")long courseId) {
        courseService.deleteCourse(courseId);
    }

    @PatchMapping("/editCourse")
    public void DtoCourse ( @RequestParam("courseId") long courseId ,
                            @RequestBody @Valid DtoCourse dtoCourse) {
        courseService.editCourse(courseId,dtoCourse);
    }

    @GetMapping("/getCourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
    }

    @DeleteMapping("/deleteContent")
    public void deleteContent (@RequestParam("contentId")long contentId ) {
        contentService.deleteContentById(contentId);
    }
    @GetMapping("/getContent")
    public ResponseEntity<List<Content>> getAllContent() {
        return ResponseEntity.status(HttpStatus.OK).body(contentService.findAll());
    }

    @PatchMapping("/editContent")
    public void editContent ( @RequestParam("contentId") long contentId,
                             @RequestBody @Valid DtoContent dtoContent) {
        contentService.editContent(contentId,dtoContent);
    }


}
