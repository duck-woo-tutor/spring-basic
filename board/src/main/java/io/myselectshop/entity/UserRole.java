package io.myselectshop.entity;

public enum UserRole {
    USER(Authority.User),
    ADMIN(Authority.Admin);

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    private static class Authority {
        static public final String User = "ROLE_USER";

        public static final String Admin = "ROLE_ADMIN";
    }
}
