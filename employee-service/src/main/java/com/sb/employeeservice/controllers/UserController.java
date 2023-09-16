package com.sb.employeeservice.controllers;


import com.sb.employeeservice.dto.UserDataDTO;
import com.sb.employeeservice.dto.UserResponseDTO;
import com.sb.employeeservice.models.User;
import com.sb.employeeservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/signin")
    @Operation(
            tags = "User Controller",
            summary = " Authenticates user and returns its JWT token.",
            description = "Gets JWT token."
    )
    public String login(@RequestParam String username,
                         @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
    @Operation(
            tags = "User Controller",
            summary = " Creates user and returns its JWT token",
            description = "Gets JWT token."
    )
    public String signup(@RequestBody UserDataDTO user) {
        return userService.signup(modelMapper.map(user, User.class));
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            tags = "User Controller",
            summary = " Deletes specific user by username",
            description = "/{username}"
    )
    @ApiResponses(value = {//responseCode = 403,description =
            @ApiResponse(responseCode = "400", description = "Something went wrong"), //
            @ApiResponse(responseCode = "403", description = "Access denied"), //
            @ApiResponse(responseCode = "404", description = "The user doesn't exist"), //
            @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
    public String delete( @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            tags = "User Controller",
            summary = " Returns specific user by username",
            description = "username"
    )
    @ApiResponses(value = {//
            @ApiResponse(responseCode = "400", description = "Something went wrong"), //
            @ApiResponse(responseCode = "403", description = "Access denied"), //
            @ApiResponse(responseCode = "404", description = "The user doesn't exist"), //
            @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
    public UserResponseDTO search(@PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDTO.class);
    }

    @GetMapping(value = "/userDetails")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @Operation(
            tags = "User Controller",
            summary = "Returns current user's data",
            description = "current user's data"
    )
    @ApiResponses(value = {//
            @ApiResponse(responseCode = "400", description = "Something went wrong"), //
            @ApiResponse(responseCode = "403", description = "Access denied"), //
            @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
    public UserResponseDTO userDetails(HttpServletRequest req) {
        return modelMapper.map(userService.userDetails(req), UserResponseDTO.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @Operation(
            tags = "User Controller",
            summary = "Returns refresh token",
            description = "token"
    )
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}
