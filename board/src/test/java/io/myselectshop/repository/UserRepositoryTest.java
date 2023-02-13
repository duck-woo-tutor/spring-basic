package io.myselectshop.repository;

import io.myselectshop.entity.User;
import io.myselectshop.entity.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("사용자 추가")
    void addUser() {
        User user = new User(
                "tester",
                "testpassword",
                "test@test.com",
                UserRole.USER
        );

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getName()).isEqualTo(user.getName());
    }

    @Test
    @DisplayName("사용자 조회")
    void findByUsername() {
        User user = new User(
                "tester",
                "testpassword",
                "test@test.com",
                UserRole.USER
        );

        user = userRepository.save(user);

        Optional<User> byName = userRepository.findByName(user.getName());

        assertThat(byName.get()).isEqualTo(user);
    }
}