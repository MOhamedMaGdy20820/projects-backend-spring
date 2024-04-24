package com.global.hr.service;

import java.util.List;

import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.entity.Department;
import com.global.hr.repository.DepartmentRepo;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepo departmentRepo;

	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	public Department findById (Long id) {
	 return departmentRepo.findById(id).orElseThrow();
	}
	
	
	public Department insert(Department dept) {

		return departmentRepo.save(dept);
	}

	public Department update(Department dept) {

//		 Department current = departmentRepo.findById(dept.getId()).orElseThrow();
//
//		current.setName(dept.getName());

		return departmentRepo.save(dept);
	}
	
	
	public List<Department> findAll(){
		
		return departmentRepo.findAll();
	}
	
	
	public int deleteByName(String deptName) {
		
		return departmentRepo.deleteByName(deptName);
	}


	public void deleteById(long id) {
		departmentRepo.deleteById(id);
	}

	public List<Employee> findByDepartmentId(long id) {
		return employeeRepo.findByDepartmentId(id);
	}
}
