package io.myselectshop.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class LoginRequestDto {
    private String username;
    private String password;
}
