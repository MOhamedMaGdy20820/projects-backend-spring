package com.app.LMS.controllers;


import com.app.LMS.dto.DtoContent;
import com.app.LMS.dto.DtoReview;
import com.app.LMS.dto.DtoStudent;
import com.app.LMS.entity.WaitingList;
import com.app.LMS.services.CourseService;
import com.app.LMS.services.InstructorService;
import com.app.LMS.services.ReviewService;
import com.app.LMS.services.WaitingListService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Instractor")
@AllArgsConstructor
public class InstructorController {

    private final WaitingListService waitingListService;
    private final CourseService courseService;
    private final InstructorService instructorService;

    @PostMapping("/addCourse/{id}")
    public ResponseEntity<String> addCourse(@RequestBody @Valid WaitingList course ,
                                            @PathVariable long id ) {
        return ResponseEntity.status(HttpStatus.OK).body(waitingListService.save(course , id));
    }

    @PutMapping("/EditCourse/{id}")
    public ResponseEntity<String> Edit(@RequestBody @Valid WaitingList course ,
                                       @PathVariable long id ) {
        return ResponseEntity.status(HttpStatus.OK).body(waitingListService.save(course , id));
    }

    @PostMapping("/addContent")
    public ResponseEntity<String> addContent(@RequestBody  List<DtoContent> content ,
                             @RequestParam("instructorId") long instructorId,
                             @RequestParam("courseId")long courseId) {

        String response =courseService.addContent(content, instructorId, courseId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getStudents")
    public ResponseEntity<List<DtoStudent>> getStudents(@RequestParam("instructorId") long instructorId,
                                                        @RequestParam("courseId")long courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                instructorService.getStudentsInCourse(instructorId,courseId));
    }

    @DeleteMapping("/deleteStudentFromCourse")
    public void deleteStudentFromCourse (@RequestParam("instructorId") long instructorId,
                               @RequestParam("courseId")long courseId,
                               @RequestParam("studentId")long studentId) {
        instructorService.deleteStudentFromCourse(instructorId, courseId, studentId);
    }






}