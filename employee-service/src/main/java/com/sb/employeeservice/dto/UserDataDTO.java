package com.sb.employeeservice.dto;

import com.sb.employeeservice.models.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDataDTO {


    private String username;

    private String email;

    private String password;

    List<UserRole> appUserRoles;

}
