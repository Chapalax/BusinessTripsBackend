package ru.mts.hackathon.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mts.hackathon.domain.repositories.UserRepository;
import ru.mts.hackathon.domain.repositories.JpaUserRepository;

@Slf4j
@Configuration
public class JpaConfig {
    @Bean
    public UserRepository credRepo(JpaUserRepository jpaUserRepository) {
        log.info("Creating JPA beans...");
        return new UserRepository(jpaUserRepository);
    }
}
