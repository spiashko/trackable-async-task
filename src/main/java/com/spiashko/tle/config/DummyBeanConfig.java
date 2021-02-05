package com.spiashko.tle.config;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyBeanConfig {

    @Bean
    public Logger dummyLogger() {
        return org.slf4j.LoggerFactory.getLogger("dummyLogger");
    }

}
