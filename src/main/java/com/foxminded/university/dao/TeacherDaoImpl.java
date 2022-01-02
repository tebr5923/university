package com.foxminded.university.dao;

import com.foxminded.university.domain.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TeacherDaoImpl implements TeacherDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Teacher> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Teacher> getAll() {
        return null;
    }

    @Override
    public void save(Teacher model) {

    }

    @Override
    public void update(Teacher model) {

    }

    @Override
    public void delete(Teacher model) {

    }

    @Override
    public void saveAll(List<Teacher> modelList) {

    }
}
