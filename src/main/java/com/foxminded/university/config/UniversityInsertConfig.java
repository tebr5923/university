package com.foxminded.university.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration
@ComponentScan("com.foxminded.university")
public class UniversityInsertConfig extends UniversityConfig {

    @Bean
    public SimpleJdbcInsert insertTeacher() {
        return new SimpleJdbcInsert(dataSource())
                .withTableName("teachers")
                .usingColumns("first_name", "last_name")
                .usingGeneratedKeyColumns("id");
    }

}