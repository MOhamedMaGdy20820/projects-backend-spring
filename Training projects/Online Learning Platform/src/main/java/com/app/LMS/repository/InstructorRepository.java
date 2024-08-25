package com.app.LMS.repository;

import com.app.LMS.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository <Instructor, Long> {
}
