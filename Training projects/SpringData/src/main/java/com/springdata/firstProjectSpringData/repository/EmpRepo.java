package com.springdata.firstProjectSpringData.repository;

import com.springdata.firstProjectSpringData.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepo extends JpaRepository<Employee,Integer> {
}
