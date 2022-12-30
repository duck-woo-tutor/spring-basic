package io.myselectshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

    public SignRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = "";
    }
}
