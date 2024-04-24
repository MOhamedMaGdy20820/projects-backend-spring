package com.springdata.firstProjectSpringData.Entity;


import com.springdata.firstProjectSpringData.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name ="employees")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

   // @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emo_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

     @Column(name = "last_name")
     private String lastName;
     private Double salary;

     public static Employee toEntity(EmployeeDto dto){
      return Employee.builder()
              .id(dto.getId())
              .firstName(dto.getFirstName())
              .lastName(dto.getLastName())
              .salary(dto.getSalary())
              .build();
     }

}













































