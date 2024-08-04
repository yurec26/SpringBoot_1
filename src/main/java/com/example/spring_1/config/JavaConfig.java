package com.example.spring_1.config;

import com.example.spring_1.model.DevProfile;
import com.example.spring_1.model.ProductionProfile;
import com.example.spring_1.model.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JavaConfig {


    @ConditionalOnProperty(name = "profile.dev", havingValue = "true")
    @Bean
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @ConditionalOnProperty(name = "profile.dev", havingValue = "false")
    @Bean
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }


}

