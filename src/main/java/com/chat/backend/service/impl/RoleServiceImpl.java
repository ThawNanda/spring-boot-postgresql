package com.chat.backend.service.impl;

import com.chat.backend.model.entity.Role;
import com.chat.backend.model.entity.RoleName;
import com.chat.backend.repository.RoleRepository;
import com.chat.backend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public void addRoles() {
        Optional<Role> adminRoleOpt =roleRepository.findByName(RoleName.ROLE_ADMIN);
        Optional<Role> userRoleOpt =roleRepository.findByName(RoleName.ROLE_USER);
        if(adminRoleOpt.isEmpty()){
            Role adminRole = new Role();
            adminRole.setName(RoleName.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }
        if(userRoleOpt.isEmpty()){
            Role userRole = new Role();
            userRole.setName(RoleName.ROLE_USER);
            roleRepository.save(userRole);
        }

    }
}
