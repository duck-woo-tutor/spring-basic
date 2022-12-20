package io.myselectshop.service;

import io.myselectshop.dto.LoginRequestDto;
import io.myselectshop.dto.SignRequestDto;
import io.myselectshop.entity.User;
import io.myselectshop.entity.UserRole;
import io.myselectshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Value(value = "${token.admin}")
    private String ADMIN_TOKEN;

    public void signup(SignRequestDto signRequestDto) {
        String username = signRequestDto.getUsername();

        userRepository.findByName(username).ifPresent(u -> {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        });

        UserRole role = UserRole.USER;
        if (signRequestDto.isAdmin()) {
            if (!signRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRole.ADMIN;
        }
        userRepository.save(new User(username, signRequestDto.getPassword(), signRequestDto.getEmail(), role));
    }

    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByName(loginRequestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
    }
}
