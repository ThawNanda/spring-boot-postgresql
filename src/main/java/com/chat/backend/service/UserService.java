package com.chat.backend.service;

import com.chat.backend.model.dto.UserDto;

import java.util.List;

public interface UserService {
    void createAdmin();
    void createUsers();
    List<UserDto> getOnlineUsers();

    List<UserDto> getOfflineUsers();
}


