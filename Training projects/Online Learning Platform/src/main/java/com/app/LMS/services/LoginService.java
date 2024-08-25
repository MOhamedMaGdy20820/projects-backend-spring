package com.app.LMS.services;

import com.app.LMS.dto.*;
import com.app.LMS.entity.Admin;
import com.app.LMS.entity.Instructor;
import com.app.LMS.entity.Student;
import com.app.LMS.repository.AdminRepository;
import com.app.LMS.repository.InstructorRepository;
import com.app.LMS.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final AdminRepository adminRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    public Boolean login(User user) {


        if ("admin".equals(user.getRole())) {

            Admin admin =new Admin();
            admin.setPassword(user.getPassword());
            admin.setEmail(user.getEmail());
            admin.setFirstName(user.getFirstName());
            admin.setLastName(user.getLastName());
            adminRepository.save(admin);
            return true;
        }
        if ("student".equals(user.getRole())) {
            Student student =new Student();
            student.setPassword(user.getPassword());
            student.setEmail(user.getEmail());
            student.setFirstName(user.getFirstName());
            student.setLastName(user.getLastName());
            studentRepository.save(student);
            return true;
        }
        if ("instructor".equals(user.getRole())) {
            Instructor instructor =new Instructor();
            instructor.setPassword(user.getPassword());
            instructor.setEmail(user.getEmail());
            instructor.setFirstName(user.getFirstName());
            instructor.setLastName(user.getLastName());
            instructorRepository.save(instructor);
            return true;
        }


        return false;
    }
}
