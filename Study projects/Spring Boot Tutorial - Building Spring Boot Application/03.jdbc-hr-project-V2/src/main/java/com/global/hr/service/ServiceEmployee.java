package com.global.hr.service;

import com.global.hr.entity.Employee;
import com.global.hr.entity.EmployeeJpa;
import com.global.hr.repsitoryJpa.EmployeeRepsJpa;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class ServiceEmployee {

    private final EmployeeRepsJpa employeeRepsJpa;

    public long count() {
        return employeeRepsJpa.count();
    }
    public Optional<EmployeeJpa> findById(Long id) {
        return employeeRepsJpa.findByEmployeeId(id);
    }
    public List<EmployeeJpa> findAll() {
        return List.of();
    }
    public void insert(EmployeeJpa employee) {
         employeeRepsJpa.save(employee);
    }
    public void update(EmployeeJpa employee) {
         employeeRepsJpa.save(employee);
    }
    public void delete(Long id) {
          employeeRepsJpa.deleteById(id);
    }
}
