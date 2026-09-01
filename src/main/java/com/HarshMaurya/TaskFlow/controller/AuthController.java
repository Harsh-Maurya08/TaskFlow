package com.HarshMaurya.TaskFlow.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.HarshMaurya.TaskFlow.dto.AuthRequest;
import com.HarshMaurya.TaskFlow.dto.AuthResponse;
import com.HarshMaurya.TaskFlow.dto.RegisterRequest;
import com.HarshMaurya.TaskFlow.entity.User;
import com.HarshMaurya.TaskFlow.security.CustomUserDetailsService;
import com.HarshMaurya.TaskFlow.security.JwtUtil;
import com.HarshMaurya.TaskFlow.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           CustomUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // NEVER store plain text
        user.setRole(request.getRole());

        User savedUser = userService.createUser(user);
        String token = jwtUtil.generateToken(savedUser); // User implements UserDetails, so this works directly
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token);
    }
}