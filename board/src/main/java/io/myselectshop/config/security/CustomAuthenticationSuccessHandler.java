package io.myselectshop.config.security;

import io.myselectshop.dto.LoginRequestDto;
import io.myselectshop.dto.SignRequestDto;
import io.myselectshop.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        userService.signup(new SignRequestDto(username, password));
        userService.login(new LoginRequestDto(username, password), response);
        response.sendRedirect("/api/shop");
    }
}
