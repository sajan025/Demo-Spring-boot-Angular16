package com.sb.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSaveDto {

    private Integer employeeId;
    private String firstName;

    private String lastName;

    private String email;

    private Double salary;

    private String department;
}
