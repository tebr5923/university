package com.foxminded.university.dao;

import com.foxminded.university.domain.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
public class JdbcTemplateCourseDaoImpl implements CourseDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertCourse;
    private final RowMapper<Course> courseRowMapper;

    @Autowired
    public JdbcTemplateCourseDaoImpl(
            JdbcTemplate jdbcTemplate,
            SimpleJdbcInsert insertCourse,
            RowMapper<Course> courseRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertCourse = insertCourse;
        this.courseRowMapper = courseRowMapper;
    }

    @Override
    public Optional<Course> getById(Integer id) {
        String sql = "SELECT * FROM courses where id=?;";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, courseRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            System.err.println("FAIL getById " + id);
        }
        return Optional.empty();
    }

    @Override
    public List<Course> getAll() {
        String sql = "SELECT * FROM courses;";
        return jdbcTemplate.query(sql, courseRowMapper);
    }

    @Override
    public void save(Course model) throws DaoException {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("name", model.getName());
        try {
            Number newId = insertCourse.executeAndReturnKey(parameters);
            model.setId(newId.intValue());
        } catch (DuplicateKeyException e) {
            System.err.println("Save ERROR: name of course already exist");
            throw new DaoException("Save ERROR: name of course already exist", e);
        }
    }

    @Override
    public void update(Course model) {
        String sql = "UPDATE courses set name=?  WHERE id=?;";
        jdbcTemplate.update(sql, model.getName(), model.getId());
    }

    @Override
    public void delete(Course model) {
        String sql = "DELETE FROM courses WHERE id=?;";
        jdbcTemplate.update(sql, model.getId());
    }

    @Override
    public int[] saveAll(List<Course> modelList) throws DaoException {
        String sql = "INSERT INTO courses (id, name) values(DEFAULT,?);";
        List<Object[]> batch = new ArrayList<>();
        for (Course course : modelList) {
            Object[] values = new Object[]{
                    course.getName()};
            batch.add(values);
        }
        int[] updateResult;
        try {
            updateResult = jdbcTemplate.batchUpdate(sql, batch);
        } catch (DuplicateKeyException e) {
            System.err.println("SaveAll ERROR: name of course already exist");
            throw new DaoException("SaveAll ERROR: name of course already exist", e);
        }
        return updateResult;
    }
}
