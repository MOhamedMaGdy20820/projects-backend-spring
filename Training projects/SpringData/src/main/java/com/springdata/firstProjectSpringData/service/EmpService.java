package com.springdata.firstProjectSpringData.service;

import com.springdata.firstProjectSpringData.Entity.Employee;
import com.springdata.firstProjectSpringData.dto.EmployeeDto;
import com.springdata.firstProjectSpringData.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpService {

    @Autowired
    private EmpRepo empRepo;

    public EmployeeDto getUser(Integer id){
        Optional<Employee> emp = this.empRepo.findById(id);  // بستخدم ال Optional عشان لو بسأل علي موضف مش موجود
        if(emp.isPresent())
            return EmployeeDto.toDto(emp.get());
        else
            return null;
    }

    public EmployeeDto saveEmp(EmployeeDto employee){
        return EmployeeDto.toDto(empRepo.save(Employee.toEntity(employee)));
    }

    public List<Employee> findAll(){
        List<Employee> emps = this.empRepo.findAll();
        return emps;
    }

    public void deleteUser(int id){
        Optional<Employee> emp = this.empRepo.findById(id);
        if(emp.isPresent())
            empRepo.deleteById(id);
    }
}














