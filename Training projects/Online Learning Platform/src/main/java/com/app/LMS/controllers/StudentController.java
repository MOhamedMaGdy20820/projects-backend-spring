package com.app.LMS.controllers;


import com.app.LMS.dto.DtoContent;
import com.app.LMS.dto.DtoCourse;
import com.app.LMS.dto.DtoReview;
import com.app.LMS.entity.Course;
import com.app.LMS.entity.Review;
import com.app.LMS.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> addCourse(@RequestParam("studentId") long studentId,
                                            @RequestParam("courseId") long courseId) {
        String response = studentService.addCourse(studentId, courseId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getCourses")
    public ResponseEntity<List<DtoCourse>> getCourses(@RequestParam("studentId") long studentId) {
        List<DtoCourse> response = studentService.getCourses(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
   }

    @GetMapping("/getLecture")
    public ResponseEntity<DtoContent> getLecture(@RequestParam("lecNumber") long lecNumber,
                                                 @RequestParam("studentId") long studentId,
                                                 @RequestParam("courseId") long courseId) {
        DtoContent response = studentService.getLecture(lecNumber,studentId, courseId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/makeReview")
    public ResponseEntity<String> makeReview(@RequestParam("studentId") long studentId,
                                           @RequestParam("courseId") long courseId,
                                           @RequestBody DtoReview dtoReview) {
        String response = studentService.makeReview(studentId, courseId, dtoReview);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/WatchingRate")
    public ResponseEntity<Double> WatchingRate(@RequestParam("studentId") long studentId,
                                               @RequestParam("courseId") long courseId
                                               ) {
        double response = studentService.WatchingRate(studentId, courseId );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<DtoCourse>> getAllCourses (){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllCourses());
    }



}

