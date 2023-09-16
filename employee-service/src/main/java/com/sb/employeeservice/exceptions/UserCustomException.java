package com.sb.employeeservice.exceptions;

public class UserCustomException extends RuntimeException{
    public UserCustomException(String message){
        super(message);
    }
}
