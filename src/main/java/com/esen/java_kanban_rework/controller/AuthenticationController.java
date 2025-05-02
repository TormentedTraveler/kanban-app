package com.esen.java_kanban_rework.controller;


import com.esen.java_kanban_rework.dto.*;
import com.esen.java_kanban_rework.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Management", description = "Operations for authentication")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate (
            @RequestBody AuthenticationRequestDTO request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<ResetPasswordResponseDTO> resetPassword (
            @RequestBody ResetPasswordRequestDTO request
    ){
        return ResponseEntity.ok(service.resetPassword(request));
    }

    @PostMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail (
            @RequestBody VerifyDTO request
    ) {
        return ResponseEntity.ok(service.verify(request));
    }
}
