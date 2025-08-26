package com.example.akage.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfig {

    @Bean
    public RuntimeWiringConfigurer dateTimeConfigurer() {
        // 파라미터 이름은 아무거나 가능하지만, 타입은 RuntimeWiring.Builder
        return builder -> builder.scalar(ExtendedScalars.DateTime);
    }
}

