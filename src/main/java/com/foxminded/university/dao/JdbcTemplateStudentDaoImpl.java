package com.foxminded.university.dao;

import com.foxminded.university.domain.model.Student;
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
public class JdbcTemplateStudentDaoImpl implements StudentDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertStudent;
    private final RowMapper<Student> studentRowMapper;

    @Autowired
    public JdbcTemplateStudentDaoImpl(
            JdbcTemplate jdbcTemplate,
            SimpleJdbcInsert insertStudent,
            RowMapper<Student> studentRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertStudent = insertStudent;
        this.studentRowMapper = studentRowMapper;
    }

    @Override
    public Optional<Student> getById(Integer id) {
        String sql = "SELECT s.id, s.group_id, s.first_name, s.last_name, g.name as group_name FROM students s left join groups g on g.id = s.group_id where s.id=?;";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, studentRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            System.err.println("FAIL getById " + id);
        }
        return Optional.empty();
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT s.id, s.group_id, s.first_name, s.last_name, g.name as group_name FROM students s join groups g on g.id = s.group_id;";

        return jdbcTemplate.query(sql, studentRowMapper);
    }

    @Override
    public void save(Student model) {
        Map<String, Object> parameters = new HashMap<>(3);
        parameters.put("group_id", model.getGroup().getId());
        parameters.put("first_name", model.getFirstName());
        parameters.put("last_name", model.getLastName());
        Number newId = insertStudent.executeAndReturnKey(parameters);
        model.setId(newId.intValue());
    }

    @Override
    public void update(Student model) {
        String sql = "UPDATE students set group_id=?, first_name=?, last_name=?  WHERE id=?;";
        jdbcTemplate.update(sql, model.getGroup().getId(), model.getFirstName(), model.getLastName(), model.getId());
    }

    @Override
    public void delete(Student model) {
        String sql = "DELETE FROM students WHERE id=?;";
        jdbcTemplate.update(sql, model.getId());
    }

    @Override
    public int[] saveAll(List<Student> modelList) {
        String sql = "INSERT INTO students (id, group_id, first_name, last_name) values(DEFAULT,?,?,?);";
        List<Object[]> batch = new ArrayList<>();
        for (Student student : modelList) {
            Object[] values = new Object[]{
                    student.getGroup().getId(), student.getFirstName(), student.getLastName()};
            batch.add(values);
        }
        return jdbcTemplate.batchUpdate(sql, batch);
    }
}
