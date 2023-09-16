package com.sb.employeeservice.dto;

import com.sb.employeeservice.models.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {


    private Integer id;

    private String username;

    private String email;

    List<UserRole> appUserRoles;

}
