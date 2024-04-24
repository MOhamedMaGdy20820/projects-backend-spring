package com.global.hr.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EmployeeJpa")
public class EmployeeJpa {

    @Id
    private Long employeeId;
    private String name;
    private Double salary;
}
