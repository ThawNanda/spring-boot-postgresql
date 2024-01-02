package com.chat.backend.model.dto;

import com.chat.backend.model.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String nickname;
    private Status status;
}
