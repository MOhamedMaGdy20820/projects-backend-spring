package com.global.hr.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
	
	Long getId();

	// close projection
	String getFirstName();
	String getLastName();
	Double getsalary();

	// open projection
	@Value("#{target.firstName + ' ' + target.lastName}")
	String getFullName();

}
