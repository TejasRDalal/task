package com.taskmanager.task.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String tokenType = "Bearer";
    private Instant expiresAt;        // optional: token expiry timestamp
    private String username;          // optional: handy for client
    private String roles;       // optional: authorities/roles

    public AuthResponse(String token) {
    }
}
