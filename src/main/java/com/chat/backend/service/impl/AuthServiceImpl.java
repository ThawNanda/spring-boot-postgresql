package com.chat.backend.service.impl;

import com.chat.backend.model.entity.RoleName;
import com.chat.backend.model.exception.ForbiddenException;
import com.chat.backend.model.request.LoginRequest;
import com.chat.backend.model.response.AuthenticationResponse;
import com.chat.backend.service.AuthService;
import com.chat.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public AuthenticationResponse authenticateUser(LoginRequest request) {

        Date expiredAt = new Date((new Date()).getTime() + 86400 * 1000);
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()));

            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.name()))) {
                String jwt = jwtService.generateToken(authentication);
                return new AuthenticationResponse(expiredAt, jwt);
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(e.getMessage());
        }
        throw new ForbiddenException("Authentication Failed!");
    }
}
