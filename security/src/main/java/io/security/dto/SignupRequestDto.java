package io.security.dto;

import io.security.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String password;
    private UserRole role;

    private String adminToken;

    public SignupRequestDto(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Boolean isAdmin() {
        return this.role == UserRole.ADMIN;
    }
}
