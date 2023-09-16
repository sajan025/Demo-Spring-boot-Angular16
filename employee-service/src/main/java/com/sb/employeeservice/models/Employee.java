package com.sb.employeeservice.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee implements Serializable {
    public static final long uId=1l;
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    @Column(name = "first_name",length = 255,nullable = false)
    private String firstName;
    @Column(name = "last_name",length = 255,nullable = false)
    private String lastName;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "department")
    private String department;
}
