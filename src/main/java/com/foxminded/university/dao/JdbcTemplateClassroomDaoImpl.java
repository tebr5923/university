package com.foxminded.university.dao;

import com.foxminded.university.domain.model.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("squid:S106") //don't use logger in this task
@Component
public class JdbcTemplateClassroomDaoImpl implements ClassroomDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertClassroom;
    private final RowMapper<Classroom> classroomRowMapper;

    @Autowired
    public JdbcTemplateClassroomDaoImpl(
            JdbcTemplate jdbcTemplate,
            SimpleJdbcInsert insertClassroom,
            RowMapper<Classroom> classroomRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertClassroom = insertClassroom;
        this.classroomRowMapper = classroomRowMapper;
    }

    @Override
    public Optional<Classroom> getById(Integer id) {
        String sql = "SELECT * FROM classrooms where id=?;";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, classroomRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            System.err.println("FAIL getById " + id);
        }
        return Optional.empty();
    }

    @Override
    public List<Classroom> getAll() {
        String sql = "SELECT * FROM classrooms;";
        return jdbcTemplate.query(sql, classroomRowMapper);
    }

    @Override
    public void save(Classroom model) {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("number", model.getNumber());
        Number newId = insertClassroom.executeAndReturnKey(parameters);
        model.setId(newId.intValue());
    }

    @Override
    public void update(Classroom model) {
        String sql = "UPDATE classrooms set number=?  WHERE id=?;";
        jdbcTemplate.update(sql, model.getNumber(), model.getId());
    }

    @Override
    public void delete(Classroom model) {
        String sql = "DELETE FROM classrooms WHERE id=?;";
        jdbcTemplate.update(sql, model.getId());
    }

    @Override
    public int[] saveAll(List<Classroom> modelList) {
        String sql = "INSERT INTO classrooms (id, number) values(DEFAULT,?);";
        List<Object[]> batch = new ArrayList<>();
        for (Classroom classroom : modelList) {
            Object[] values = new Object[]{
                    classroom.getNumber()};
            batch.add(values);
        }
        return jdbcTemplate.batchUpdate(sql, batch);
    }
}
