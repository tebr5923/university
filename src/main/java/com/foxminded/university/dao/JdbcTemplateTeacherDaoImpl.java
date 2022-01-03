package com.foxminded.university.dao;

import com.foxminded.university.domain.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcTemplateTeacherDaoImpl implements TeacherDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateTeacherDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Teacher> getById(Integer id) {
        String sql = "SELECT * FROM teachers where id=?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Teacher.class), id).stream().findAny();

    }

    @Override
    public List<Teacher> getAll() {
        String sql = "SELECT * FROM teachers;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Teacher.class));
    }

    @Override
    public void save(Teacher model) {
        String sql = "INSERT INTO teachers (id, first_name, last_name) values(DEFAULT,?,?);";
        jdbcTemplate.update(sql, model.getFirstName(), model.getLastName());
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

        List<Object[]> batch = new ArrayList<Object[]>();
        for (Teacher teacher : modelList) {
            Object[] values = new Object[]{
                    teacher.getFirstName(), teacher.getLastName()};
            batch.add(values);
        }
        return jdbcTemplate.batchUpdate(sql, batch);
    }
}
