package com.chat.backend.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuthenticationResponse {

    private String type ="Bearer";
    private Date expiredAt;
    private String accessToken;

    public AuthenticationResponse(Date expiredAt, String accessToken) {
        this.expiredAt = expiredAt;
        this.accessToken = accessToken;
    }
}
