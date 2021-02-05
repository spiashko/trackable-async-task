package com.spiashko.rfetchexample.config;

import com.spiashko.rfetchexample.utils.EntityManagerHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class JpaConfig {

    @Bean
    EntityManagerHolder entityManagerHolder(EntityManager entityManager) {
        return EntityManagerHolder.createInstance(entityManager);
    }

}
