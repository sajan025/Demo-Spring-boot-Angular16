package com.sb.employeeservice.controllers;

import com.sb.employeeservice.dto.EmployeeSaveDto;
import com.sb.employeeservice.dto.RestResponse;
import com.sb.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    //delete employee by id
    @DeleteMapping("/{id}")
    @Operation(tags="Employee Controller",
            summary = "Delete a employee. ",
            description = "Delete a employee by id.")
    public ResponseEntity<Map<String,Object>> deleteById(@PathVariable Integer id){
        Map<String,Object> responseBody=new HashMap<>();
       boolean isDeleted= employeeService.deleteEmployeeById(id);
       if(isDeleted) {
           responseBody.put("status",true);
           responseBody.put("message","Deleted successfully!");
           return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.OK);
       }else {
           responseBody.put("status",false);
           responseBody.put("message","Something went wrong.");
           return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.BAD_REQUEST);
       }
    }

    //update employee data
    @PutMapping("update-employee")
    @Operation(
            tags="Employee Controller",
            summary = "Update a employee",
            description = "Update employee data",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Employee",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = EmployeeSaveDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Update employee",
                                                    summary = "Update employee Example",
                                                    description = "Complete request with all available fields for employee.",
                                                    value = "{\n" +
                                                            "  \"employeeId\": 1,\n" +
                                                            "  \"firstName\": \"string1\",\n" +
                                                            "  \"lastName\": \"string1\",\n" +
                                                            "  \"email\": \"string1\",\n" +
                                                            "  \"department\": \"string1\",\n" +
                                                            "  \"salary\": 25000\n" +
                                                            "}"
                                            )
                                    }
                            ),
                    }
            )
    )
    public ResponseEntity<RestResponse<EmployeeSaveDto>> updateEmployeeData(@RequestBody EmployeeSaveDto employeeSaveDto){
        EmployeeSaveDto updatedEmployee=employeeService.updateEmployee(employeeSaveDto);
        return ResponseEntity.ok(RestResponse.of(updatedEmployee));
    }

    //get all employees
    @GetMapping
    @Operation(tags = "Employee Controller",
    summary = "Find employees",
    description = "Find all the employees for employee table.")
    public ResponseEntity<RestResponse<List<EmployeeSaveDto>>> findAllEmployees(){
        List<EmployeeSaveDto> empList=employeeService.findAllEmployees();
        return ResponseEntity.ok(RestResponse.of(empList));
    }
    //save employee data
    @PostMapping("/save-employee")
    @Operation(
            tags="Employee Controller",
            summary = "Save a employee",
            description = "Saves a new employee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Employee",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = EmployeeSaveDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new employee",
                                                    summary = "New employee Example",
                                                    description = "Complete request with all available fields for employee.",
                                                    value = "{\n" +
                                                            "  \"firstName\": \"string1\",\n" +
                                                            "  \"lastName\": \"string1\",\n" +
                                                            "  \"email\": \"string1\",\n" +
                                                            "  \"department\": \"string1\",\n" +
                                                            "  \"salary\": 25000\n" +
                                                            "}"
                                            )
                                    }
                            ),
                    }
            )
    )
    public ResponseEntity<RestResponse<EmployeeSaveDto>> saveEmployeeData(@RequestBody EmployeeSaveDto employeeSaveDto){
        EmployeeSaveDto employeeDto=employeeService.saveEmployee(employeeSaveDto);
        return ResponseEntity.ok(RestResponse.of(employeeDto));
    }
}
