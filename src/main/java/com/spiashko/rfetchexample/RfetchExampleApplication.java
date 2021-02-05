package com.spiashko.rfetchexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaAuditing
@EnableTransactionManagement
@SpringBootApplication
public class RfetchExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RfetchExampleApplication.class, args);
    }

}
