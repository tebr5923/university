package com.foxminded.university.config;

import com.foxminded.university.domain.model.Classroom;
import com.foxminded.university.domain.model.Course;
import com.foxminded.university.domain.model.Group;
import com.foxminded.university.domain.model.Lecture;
import com.foxminded.university.domain.model.Student;
import com.foxminded.university.domain.model.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public RowMapper<Lecture> lectureRowMapper() {
        return (resultSet, i) -> {
            Lecture lecture = new Lecture();
            lecture.setId(resultSet.getInt("id"));
            lecture.setDateTime(resultSet.getObject("date_time", LocalDateTime.class));
            lecture.setClassroom(mapClassroom(resultSet));
            lecture.setTeacher(mapTeacher(resultSet));
            lecture.setGroup(mapGroup(resultSet));
            lecture.setCourse(mapCourse(resultSet));

            return lecture;
        };
    }

    private Classroom mapClassroom(ResultSet resultSet) throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setId(resultSet.getInt("classroom_id"));
        classroom.setNumber(resultSet.getInt("classroom_number"));
        return classroom;
    }

    private Teacher mapTeacher(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt("teacher_id"));
        teacher.setFirstName(resultSet.getString("teacher_first_name"));
        teacher.setLastName(resultSet.getString("teacher_last_name"));
        return teacher;
    }

    private Group mapGroup(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("group_id"));
        group.setName(resultSet.getString("group_name"));
        return group;
    }

    private Course mapCourse(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getInt("course_id"));
        course.setName(resultSet.getString("course_name"));
        return course;
    }

}
