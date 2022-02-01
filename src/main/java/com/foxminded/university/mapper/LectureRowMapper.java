package com.foxminded.university.mapper;

import com.foxminded.university.domain.model.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class LectureRowMapper implements RowMapper<Lecture> {
    @Override
    public Lecture mapRow(ResultSet resultSet, int i) throws SQLException {
        Lecture lecture = new Lecture();
        lecture.setId(resultSet.getInt("id"));
        lecture.setDateTime(resultSet.getObject("date_time", LocalDateTime.class));
        lecture.setClassroom(mapClassroom(resultSet));
        lecture.setTeacher(mapTeacher(resultSet));
        lecture.setGroup(mapGroup(resultSet));
        lecture.setCourse(mapCourse(resultSet));

        return lecture;
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
