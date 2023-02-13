package io.myselectshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.myselectshop.dto.SignRequestDto;
import io.myselectshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("회원가입 (성공)")
    void signup() throws Exception {
        SignRequestDto request = SignRequestDto.builder()
                .admin(false)
                .username("tester")
                .password("testpassword")
                .email("test@test.com")
                .build();

        when(userService.signup(any(SignRequestDto.class)))
                .thenReturn(anyLong());

        ObjectMapper mapper = new ObjectMapper();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
        );

        resultActions.andExpect(status().is3xxRedirection());
    }

    @Test
    @DisplayName("회원가입 (실패) - 아이디 5 ~ 20")
    void signup_failed_id() throws Exception {
        SignRequestDto request = SignRequestDto.builder()
                .admin(false)
                .username("test")
                .password("testpassword")
                .email("test@test.com")
                .build();
        ObjectMapper mapper = new ObjectMapper();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
        );

        resultActions.andExpect(status().isBadRequest());
    }
}