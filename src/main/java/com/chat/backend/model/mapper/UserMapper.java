package com.chat.backend.model.mapper;

import com.chat.backend.model.dto.UserDto;
import com.chat.backend.model.entity.User;

import java.util.List;

public interface UserMapper {
    List<UserDto> toDto(List<User> users);

    UserDto toDto(User user);
}
