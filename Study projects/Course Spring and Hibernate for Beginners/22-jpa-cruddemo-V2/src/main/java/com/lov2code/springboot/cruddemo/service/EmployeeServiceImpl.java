package com.lov2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lov2code.springboot.cruddemo.dao.EmployeeDAO;
import com.lov2code.springboot.cruddemo.entity.Employee;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
				
    // انا عندي اكتر من كلاس ممكن تعمله انجكت ف البرنامج مش بيعرف يستخدم انهو واحد فيهم ف بعرفه عن طريق ان اكتب اسم الكلاس اللي انا عايزها تعمل حقن للأوبجكت
	// bean id = name of class bus first char is small 
	 // bean id = employeeDAOJpaImpl = (E)mployeeDAOJpaImpl
	
	public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl")EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() { 
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		return employeeDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeDAO.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		
		employeeDAO.deleteById(theId);
	} 

}































