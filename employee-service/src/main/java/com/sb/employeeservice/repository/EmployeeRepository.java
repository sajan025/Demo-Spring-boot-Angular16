package com.sb.employeeservice.repository;

import com.sb.employeeservice.dto.EmployeeSaveDto;
import com.sb.employeeservice.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


}
