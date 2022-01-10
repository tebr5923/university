package com.foxminded.university.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@Configuration
public class UniversityInsertConfig {

    @Bean
    public SimpleJdbcInsert insertTeacher(DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource)
                .withTableName("teachers")
                .usingColumns("first_name", "last_name")
                .usingGeneratedKeyColumns("id");
    }

    @Bean
    public SimpleJdbcInsert insertGroup(DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource)
                .withTableName("groups")
                .usingColumns("name")
                .usingGeneratedKeyColumns("id");
    }



}
