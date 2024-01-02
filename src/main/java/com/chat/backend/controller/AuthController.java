package com.chat.backend.controller;

import com.chat.backend.model.request.LoginRequest;
import com.chat.backend.model.response.AuthenticationResponse;
import com.chat.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request){
         AuthenticationResponse response =authService.authenticateUser(request);
         return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
