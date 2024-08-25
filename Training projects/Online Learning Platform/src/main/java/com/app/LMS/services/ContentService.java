package com.app.LMS.services;


import com.app.LMS.dto.DtoContent;
import com.app.LMS.entity.Content;
import com.app.LMS.entity.Course;
import com.app.LMS.entity.Student;
import com.app.LMS.exceptions.CustomException;
import com.app.LMS.repository.ContentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public Content findById(Long id) {
        return  contentRepository.findById(id).orElseThrow(() ->
                new CustomException("Content is not available or has not been added yet."));
    }

    public Student addStudent(Student student , Content content) {
        content.setStudent(student);
        contentRepository.save(content);
        return student;
    }


    public void deleteContentById(long contentId) {
        contentRepository.deleteById(contentId);
    }

    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    public void editContent(long contentId, DtoContent dtoContent) {
        Content content = findById(contentId);
        content.setLectureTitle(dtoContent.getLectureTitle());
        content.setLectureContent(dtoContent.getLectureContent());
        contentRepository.save(content);
    }
}
