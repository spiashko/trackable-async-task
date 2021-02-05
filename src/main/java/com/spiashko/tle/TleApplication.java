package com.spiashko.tle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableJpaAuditing
@EnableTransactionManagement
@SpringBootApplication
public class TleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TleApplication.class, args);
    }

}
