package com.example.board.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    @Bean
    public Thymeleaf4Properties thymeleaf4Properties() {
        return new Thymeleaf4Properties();
    }

    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver,
            Thymeleaf4Properties thymeleaf4Properties
    ) {
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf4Properties.decoupledLogic());

        return defaultTemplateResolver;
    }


    @Getter
    @RequiredArgsConstructor
    @ConfigurationProperties("spring.thymeleaf4")
    public static class Thymeleaf4Properties {

        /**
         * Use Thymeleaf 4 Decoupled Logic
         */

        public boolean decoupledLogic() {
            return false;
        }

    }



}
