package com.global.hr.controller;

import com.global.hr.entity.Employee;
import com.global.hr.entity.EmployeeJpa;
import com.global.hr.service.ServiceEmployee;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
@AllArgsConstructor
public class jpaController {


    private final ServiceEmployee serviceEmployee;

    @GetMapping("/count")
    public long countEmployees() {
        return serviceEmployee.count();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeJpa> findById(@PathVariable Long id) {

        return serviceEmployee.findById(id);
    }

    @GetMapping("")
    public List<EmployeeJpa> findAll() {
        return serviceEmployee.findAll();
    }

    @GetMapping("hello")
    public String hallo (){
        return "hello world";
    }
}
