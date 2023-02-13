package io.myselectshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignRequestDto {
    private Boolean admin = false;
    private String adminToken = "";

    @NotBlank
    @Size(min = 5, max = 50)
    private String username;
    private String password;
    private String email;

    public boolean isAdmin() {
        return admin;
    }
}
