package com.app.LMS.repository;

import com.app.LMS.entity.Content;
import com.app.LMS.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

}
