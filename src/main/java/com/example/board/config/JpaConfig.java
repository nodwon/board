package com.example.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing //audting서비스 설정
@Configuration
public class JpaConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("uno"); // spring security 넣으면 인증기능을 붙이게되면 수정하자
        // 오디팅할때마다 uno라는 사람이름을 넣은다.
    }
}
