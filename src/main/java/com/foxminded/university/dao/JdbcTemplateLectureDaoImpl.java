package com.foxminded.university.dao;

import com.foxminded.university.domain.model.Lecture;
import com.foxminded.university.domain.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("squid:S106") //don't use logger in this task
@Component
public class JdbcTemplateLectureDaoImpl implements LectureDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertLecture;
    private final RowMapper<Lecture> lectureRowMapper;

    @Autowired
    public JdbcTemplateLectureDaoImpl(
            JdbcTemplate jdbcTemplate,
            @Qualifier("insertLecture")SimpleJdbcInsert insertLecture,
            RowMapper<Lecture> lectureRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertLecture = insertLecture;
        this.lectureRowMapper = lectureRowMapper;
    }

    @Override
    public Optional<Lecture> getById(Integer id) {
     //   String sql = "SELECT s.id, s.group_id, s.first_name, s.last_name, g.name as group_name FROM students s left join groups g on g.id = s.group_id where s.id=?;";
        String sql = "SELECT l.id, l.date_time  FROM lectures l where id=?;";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, lectureRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            System.err.println("FAIL getById " + id);
        }
        return Optional.empty();
    }

    @Override
    public List<Lecture> getAll() {
        return null;
    }

    @Override
    public void save(Lecture model) {

    }

    @Override
    public void update(Lecture model) {

    }

    @Override
    public void delete(Lecture model) {

    }

    @Override
    public int[] saveAll(List<Lecture> modelList) {
        return new int[0];
    }
}
