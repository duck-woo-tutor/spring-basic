package io.myselectshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignRequestDto {
    private final Boolean admin = false;
    private final String adminToken = "";
    private String username;
    private String password;
    private String email;

    public boolean isAdmin() {
        return admin;
    }
}
