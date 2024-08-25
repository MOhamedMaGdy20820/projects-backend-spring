package com.app.LMS.services;

import com.app.LMS.entity.Student;
import com.app.LMS.repository.AdminRepository;
import com.app.LMS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;



}
