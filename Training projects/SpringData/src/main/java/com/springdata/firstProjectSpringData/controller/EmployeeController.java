package com.springdata.firstProjectSpringData.controller;


import com.springdata.firstProjectSpringData.Entity.Employee;
import com.springdata.firstProjectSpringData.dto.EmployeeDto;
import com.springdata.firstProjectSpringData.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmpService empService;

    @GetMapping ("/get-emp")
    public EmployeeDto getUser(@RequestParam Integer id){
        return empService.getUser(id);
    }

    @PostMapping("/save-emp")
    public EmployeeDto save(@RequestBody EmployeeDto employee){
        return empService.saveEmp(employee);
    }

    @PostMapping("/updata-emp")
    public EmployeeDto Updata(@RequestBody EmployeeDto employee){
        return empService.saveEmp(employee);
    }


    @GetMapping("/getAll")
    public  List<Employee> getUsers(){
        List<Employee> emps = empService.findAll();
        return emps;
    }

    @DeleteMapping ("/delete-emp")
    public void delete(@RequestParam Integer id){
         empService.deleteUser(id);
    }
}























