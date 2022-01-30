package com.foxminded.university.config;

import com.foxminded.university.domain.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

@Configuration
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


    @Bean
    public RowMapper<Student> studentRowMapper() {
        return (resultSet, i) -> {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setFirstName(resultSet.getString("first_name"));
            student.setLastName(resultSet.getString("last_name"));
            Group group = new Group();
            group.setId(resultSet.getInt("group_id"));
            group.setName(resultSet.getString("group_name"));
            student.setGroup(group);
            return student;
        };
    }

    @Bean
    public RowMapper<Lecture> lectureRowMapper(){
        return (resultSet, i) -> {
            Lecture lecture = new Lecture();
            lecture.setId(resultSet.getInt("id"));
            lecture.setDateTime(resultSet.getObject("date_time", LocalDateTime.class));

            return lecture;
        };
    }

}
