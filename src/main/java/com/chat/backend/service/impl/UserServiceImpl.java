package com.chat.backend.service.impl;

import com.chat.backend.model.dto.UserDto;
import com.chat.backend.model.entity.Role;
import com.chat.backend.model.entity.RoleName;
import com.chat.backend.model.entity.Status;
import com.chat.backend.model.entity.User;
import com.chat.backend.model.mapper.UserMapper;
import com.chat.backend.repository.RoleRepository;
import com.chat.backend.repository.UserRepository;
import com.chat.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    @Override
    public void createAdmin() {
        List<User> userList = userRepository.findByRoleAdmin(RoleName.ROLE_ADMIN);
        if (userList.isEmpty()) {
            User user = new User();
            user.setUsername("admin");
            user.setNickname("admin");
            user.setPassword(encoder.encode("12345678"));
            Role role = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow();
            user.setRoles(Collections.singleton(role));
            userRepository.save(user);
        }
    }

    @Override
    public void createUsers() {
        List<Integer> users = List.of(1,2,3,4,5,6,7,8,9);
        users.forEach(u->{
            User user = new User();
            user.setUsername("user"+u);
            user.setNickname("user"+u);
            user.setPassword(encoder.encode("12345678"));
            Role role = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow();
            user.setRoles(Collections.singleton(role));
            user.setStatus(Status.ONLINE);
            if(Objects.equals(user.getUsername(),"user5")){
                user.setStatus(Status.OFFLINE);
            }
            userRepository.save(user);
        });
    }

    @Override
    public List<UserDto> getOnlineUsers() {

        List<User>users = userRepository.getOnlineUsers();

        return userMapper.toDto(users);
    }

    @Override
    public List<UserDto> getOfflineUsers() {
        List<User>users = userRepository.getOfflineUsers();

        return userMapper.toDto(users);
    }
}
