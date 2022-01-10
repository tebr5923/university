package com.foxminded.university.config;

import com.foxminded.university.domain.model.Group;
import com.foxminded.university.domain.model.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
public class UniversityMapperConfig extends UniversityConfig{
    @Bean
    public BeanPropertyRowMapper<Teacher> teacherRowMapper() {
        return new BeanPropertyRowMapper<>(Teacher.class);
    }

    @Bean
    public BeanPropertyRowMapper<Group> groupRowMapper() {
        return new BeanPropertyRowMapper<>(Group.class);
    }
}
