package com.sb.employeeservice.service;

import com.sb.employeeservice.dto.EmployeeSaveDto;
import com.sb.employeeservice.models.Employee;
import com.sb.employeeservice.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired ModelMapper mapper;
    @Autowired
    EmployeeRepository employeeRepository;
    public EmployeeSaveDto saveEmployee(EmployeeSaveDto employeeSaveDto) {
        Employee employee=mapper.map(employeeSaveDto,Employee.class);
        return mapper.map(employeeRepository.save(employee),EmployeeSaveDto.class);
    }

    public List<EmployeeSaveDto> findAllEmployees() {
        List<Employee> empList=employeeRepository.findAll();
        List<EmployeeSaveDto> empDtoList=new ArrayList<>(empList.size());
        for(Employee employee:empList){
            empDtoList.add(mapper.map(employee,EmployeeSaveDto.class));
        }
        return empDtoList;
    }


    public EmployeeSaveDto updateEmployee(EmployeeSaveDto employeeSaveDto) {
        Employee employee= employeeRepository.save(mapper.map(employeeSaveDto,Employee.class));
        EmployeeSaveDto updatedEmployee=mapper.map(employee,EmployeeSaveDto.class);
        return updatedEmployee;
    }

    public boolean deleteEmployeeById(Integer id) {
       try {
           employeeRepository.deleteById(id);
           return true;
       }catch (Exception e){
           return false;
       }
    }
}
