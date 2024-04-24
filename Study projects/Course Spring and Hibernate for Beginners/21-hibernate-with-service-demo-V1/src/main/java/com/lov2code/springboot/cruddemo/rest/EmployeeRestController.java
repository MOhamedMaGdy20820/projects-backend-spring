package com.lov2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.lov2code.springboot.cruddemo.entity.Employee;
import com.lov2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	
	
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}


	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findALL(){
		return employeeService.findAll();
	}
	
	// add mappinf for GET /employee/{employeeId}
	@GetMapping("/employee/{employeeId}")
	public Employee getEmployee (@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null) {
			throw new RuntimeException("Employee id not found - "+employeeId);
			
		}
		return theEmployee;
	}
	

	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		// also jst in case they pass an id in the JSON .... set id to 0
		// this is to force a save of new item ... instead of update
		
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	// add mapping fo PUT / employees - update existing employee
	
	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	@DeleteMapping("/employee/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee thempEmployee = employeeService.findById(employeeId);
			
		// throw exception if null
		if(thempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + employeeId);
		}
		employeeService.deleteById(employeeId);
		
		return "Deleted employee id - " + employeeId ;
	
	}
	


}









































