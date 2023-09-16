package com.sb.employeeservice.response;

import com.sb.employeeservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 4744643015194204171L;
    private String status;
    private String message;
    private String AUTH_TOKEN;

    private User user;
}
