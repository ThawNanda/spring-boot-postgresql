package com.chat.backend.controller;

import com.chat.backend.model.dto.UserDto;
import com.chat.backend.model.entity.User;
import com.chat.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/create-users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addUsers(){
        userService.createUsers();
        return ResponseEntity.ok("Successfully created.");
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDto> getOnlineUsers(@RequestParam boolean online){
        if(!online){
            return userService.getOfflineUsers();
        }
        return userService.getOnlineUsers();
    }


}
