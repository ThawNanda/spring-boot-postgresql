package com.chat.backend.service;

import com.chat.backend.model.request.LoginRequest;
import com.chat.backend.model.response.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse authenticateUser(LoginRequest request);
}
