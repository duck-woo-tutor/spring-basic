package io.security.config.security;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SecurityExceptionDto {
    private Integer statusCode;
    private String msg;

    public SecurityExceptionDto(Integer statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
