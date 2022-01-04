package com.foxminded.university.dao;

import com.foxminded.university.domain.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcTemplateTeacherDaoImpl implements TeacherDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertTeacher;
    private final RowMapper<Teacher> teacherRowMapper;

    @Autowired
    public JdbcTemplateTeacherDaoImpl(
            JdbcTemplate jdbcTemplate,
            @Qualifier("insertTeacher") SimpleJdbcInsert insertTeacher,
            RowMapper<Teacher> teacherRowMapper
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertTeacher = insertTeacher;
        this.teacherRowMapper = teacherRowMapper;
    }

    @Override
    public Optional<Teacher> getById(Integer id) {
        String sql = "SELECT * FROM teachers where id=?;";
        return jdbcTemplate.query(sql, teacherRowMapper, id).stream().findAny();
    }

    @Override
    public List<Teacher> getAll() {
        String sql = "SELECT * FROM teachers;";
        return jdbcTemplate.query(sql, teacherRowMapper);
    }

    @Override
    public void save(Teacher model) {
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("first_name", model.getFirstName());
        parameters.put("last_name", model.getLastName());
        Number newId = insertTeacher.executeAndReturnKey(parameters);
        model.setId(newId.intValue());
    }

    @Override
    public void update(Teacher model) {
        String sql = "UPDATE teachers set first_name=?, last_name=?  WHERE id=?;";
        jdbcTemplate.update(sql, model.getFirstName(), model.getLastName(), model.getId());
    }

    @Override
    public void delete(Teacher model) {
        String sql = "DELETE FROM teachers WHERE id=?;";
        jdbcTemplate.update(sql, model.getId());
    }

    @Override
    public int[] saveAll(List<Teacher> modelList) {
        String sql = "INSERT INTO teachers (id, first_name, last_name) values(DEFAULT,?,?);";

        List<Object[]> batch = new ArrayList<>();
        for (Teacher teacher : modelList) {
            Object[] values = new Object[]{
                    teacher.getFirstName(), teacher.getLastName()};
            batch.add(values);
        }
        return jdbcTemplate.batchUpdate(sql, batch);
    }
}
