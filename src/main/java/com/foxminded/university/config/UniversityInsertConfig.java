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

    @Bean
    public SimpleJdbcInsert insertCourse(DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource)
                .withTableName("courses")
                .usingColumns("name")
                .usingGeneratedKeyColumns("id");
    }

    @Bean
    public SimpleJdbcInsert insertClassroom(DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource)
                .withTableName("classrooms")
                .usingColumns("number")
                .usingGeneratedKeyColumns("id");
    }

    @Bean
    public SimpleJdbcInsert insertStudent(DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource)
                .withTableName("students")
                .usingColumns("group_id", "first_name", "last_name")
                .usingGeneratedKeyColumns("id");
    }

    @Bean SimpleJdbcInsert insertLecture(DataSource dataSource){
        return new SimpleJdbcInsert(dataSource)
                .withTableName("lectures")
                .usingColumns("date_time", "classroom_id", "teacher_id", "group_id", "course_id")
                .usingGeneratedKeyColumns("id");
    }

}
