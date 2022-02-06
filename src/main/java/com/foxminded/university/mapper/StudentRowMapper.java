package com.foxminded.university.mapper;

import com.foxminded.university.domain.model.Group;
import com.foxminded.university.domain.model.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        Group group = new Group();
        group.setId(resultSet.getInt("group_id"));
        group.setName(resultSet.getString("group_name"));
        student.setGroup(group);
        return student;
    }
}
