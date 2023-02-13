package io.myselectshop.service;

import io.myselectshop.config.security.JwtUtil;
import io.myselectshop.dto.LoginRequestDto;
import io.myselectshop.dto.SignRequestDto;
import io.myselectshop.entity.User;
import io.myselectshop.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Spy
    private JwtUtil jwtUtil = new JwtUtil(username -> null);

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void prepare() {
        ReflectionTestUtils.setField(
                jwtUtil,
                "secretKey",
                "c3ByaW5nIHRlc3R0ZXN0dGVzdHRlc3R0ZXN0dGVzdHRlc3R0ZXN0dGVzdHRlc3R0ZXN0dGVzdHRlc3Qgand0");
                jwtUtil.init();
    }

    @Test
    @DisplayName("회원 가입")
    void signup() {
        SignRequestDto request = SignRequestDto.builder()
                .admin(false)
                .username("tester")
                .password("testpassword")
                .email("test@test.com")
                .build();

        when(userRepository.findByName(any(String.class)))
                .thenReturn(Optional.empty());

        userService.signup(request);

        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("회원 가입 - 중복 사용자")
    void signup_중복사용자() {
        SignRequestDto request = SignRequestDto.builder()
                .admin(false)
                .username("tester")
                .password("testpassword")
                .email("test@test.com")
                .build();

        User user = new User("tester", passwordEncoder.encode("testpassword"), null, null);
        user.setIdForTest();

        when(userRepository.findByName(any(String.class)))
                .thenReturn(Optional.of(user));

        assertThrows(IllegalArgumentException.class, () -> userService.signup(request));
    }

    @Test
    @DisplayName("로그인")
    void login() {
        LoginRequestDto request = LoginRequestDto.builder()
                .username("tester")
                .password("testpassword")
                .build();

        User user = new User("tester", passwordEncoder.encode("testpassword"), null, null);
        user.setIdForTest();

        when(userRepository.findByName(any(String.class)))
                .thenReturn(Optional.of(user));

        MockHttpServletResponse servletResponse = new MockHttpServletResponse();

        userService.login(request, servletResponse);

        verify(userRepository, times(1)).findByName(anyString());
    }
}