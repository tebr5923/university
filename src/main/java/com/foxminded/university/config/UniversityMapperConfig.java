package com.foxminded.university.config;

import com.foxminded.university.domain.model.Classroom;
import com.foxminded.university.domain.model.Course;
import com.foxminded.university.domain.model.Group;
import com.foxminded.university.domain.model.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
@ComponentScan("com.foxminded.university.mapper")
public class UniversityMapperConfig {
    @Bean
    public BeanPropertyRowMapper<Teacher> teacherRowMapper() {
        return new BeanPropertyRowMapper<>(Teacher.class);
    }

    @Bean
    public BeanPropertyRowMapper<Group> groupRowMapper() {
        return new BeanPropertyRowMapper<>(Group.class);
    }

    @Bean
    public BeanPropertyRowMapper<Course> courseRowMapper() {
        return new BeanPropertyRowMapper<>(Course.class);
    }

    @Bean
    public BeanPropertyRowMapper<Classroom> classroomRowMapper() {
        return new BeanPropertyRowMapper<>(Classroom.class);
    }

}
