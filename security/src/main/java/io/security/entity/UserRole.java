package io.security.entity;

import lombok.Getter;

@Getter
public enum UserRole {
    USER(Authority.USER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public static class Authority {
        static public final String USER = "ROLE_USER";
        static public final String ADMIN = "ROLE_ADMIN";
    }
}
