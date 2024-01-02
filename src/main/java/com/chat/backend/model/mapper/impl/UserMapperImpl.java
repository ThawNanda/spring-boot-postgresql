package com.chat.backend.model.mapper.impl;

import com.chat.backend.model.dto.UserDto;
import com.chat.backend.model.entity.User;
import com.chat.backend.model.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public List<UserDto> toDto(List<User> users) {
        return users.stream().map(this::toDto).toList();
    }

    @Override
    public UserDto toDto(User user) {
        if(user==null)return null;
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setNickname(user.getNickname());
        userDto.setStatus(user.getStatus());
        return userDto;
    }
}
