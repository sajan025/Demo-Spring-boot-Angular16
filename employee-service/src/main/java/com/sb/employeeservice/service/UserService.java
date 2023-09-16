package com.sb.employeeservice.service;

import com.sb.employeeservice.exceptions.CustomException;
import com.sb.employeeservice.exceptions.UserCustomException;
import com.sb.employeeservice.models.User;
import com.sb.employeeservice.repository.UserRepository;
import com.sb.employeeservice.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByUserName(username).getAppUserRoles());
        } catch (Exception e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User appUser) {
        if (!userRepository.existsByUserName(appUser.getUserName())) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            userRepository.save(appUser);
            return jwtTokenProvider.createToken(appUser.getUserName(), appUser.getAppUserRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUserName(username);
    }

    public User search(String username) {
        User appUser = userRepository.findByUserName(username);
        if (appUser == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return appUser;
    }

    public User userDetails(HttpServletRequest req) {
        return userRepository.findByUserName(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUserName(username).getAppUserRoles());
    }
}
