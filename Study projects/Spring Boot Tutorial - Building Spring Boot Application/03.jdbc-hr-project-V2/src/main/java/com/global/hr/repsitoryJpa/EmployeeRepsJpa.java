package com.global.hr.repsitoryJpa;


import com.global.hr.entity.EmployeeJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepsJpa extends JpaRepository<EmployeeJpa, Long> {

	Optional<EmployeeJpa> findByEmployeeId(Long id);

	List<EmployeeJpa> findAll();

}
