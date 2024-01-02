package com.chat.backend.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtService {

    String extractUsername(String jwt);

    boolean isTokenValid(String jwt);

    Claims getClaims(String jwt);

    String generateToken(Authentication authentication);
}
