package com.foxminded.university.dao;

import com.foxminded.university.domain.model.Lecture;
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
public class JdbcTemplateLectureDaoImpl implements LectureDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertLecture;
    private final RowMapper<Lecture> lectureRowMapper;

    @Autowired
    public JdbcTemplateLectureDaoImpl(
            JdbcTemplate jdbcTemplate,
            SimpleJdbcInsert insertLecture,
            RowMapper<Lecture> lectureRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertLecture = insertLecture;
        this.lectureRowMapper = lectureRowMapper;
    }

    @Override
    public Optional<Lecture> getById(Integer id) {
        String sql = "SELECT l.id, l.date_time, l.classroom_id, l.teacher_id, l.group_id, l.course_id, " +
                "c.number as classroom_number, " +
                "t.first_name as teacher_first_name, t.last_name as teacher_last_name, " +
                "g.name as group_name, " +
                "crs.name as course_name " +
                "FROM lectures l " +
                "join classrooms c on c.id = l.classroom_id " +
                "join teachers t on t.id = l.teacher_id " +
                "join groups g on g.id = l.group_id " +
                "join courses crs on crs.id = l.course_id " +
                "where l.id=?;";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, lectureRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            System.err.println("FAIL getById " + id);
        }
        return Optional.empty();
    }

    @Override
    public List<Lecture> getAll() {
        String sql = "SELECT l.id, l.date_time, l.classroom_id, l.teacher_id, l.group_id, l.course_id, " +
                "c.number as classroom_number, " +
                "t.first_name as teacher_first_name, t.last_name as teacher_last_name, " +
                "g.name as group_name, " +
                "crs.name as course_name " +
                "FROM lectures l " +
                "join classrooms c on c.id = l.classroom_id " +
                "join teachers t on t.id = l.teacher_id " +
                "join groups g on g.id = l.group_id " +
                "join courses crs on crs.id = l.course_id ";
        return jdbcTemplate.query(sql, lectureRowMapper);
    }

    @Override
    public void save(Lecture model) {
        Map<String, Object> parameters = new HashMap<>(5);
        parameters.put("date_time", model.getDateTime());
        parameters.put("classroom_id", model.getClassroom().getId());
        parameters.put("teacher_id", model.getTeacher().getId());
        parameters.put("group_id", model.getGroup().getId());
        parameters.put("course_id", model.getCourse().getId());
        Number newId = insertLecture.executeAndReturnKey(parameters);
        model.setId(newId.intValue());
    }

    @Override
    public void update(Lecture model) {
        String sql = "UPDATE lectures set date_time=?, classroom_id=?, teacher_id=?, group_id=?, course_id=?  WHERE id=?;";
        jdbcTemplate.update(sql, model.getDateTime(), model.getClassroom().getId(), model.getTeacher().getId()
                , model.getGroup().getId(), model.getCourse().getId(), model.getId());
    }

    @Override
    public void delete(Lecture model) {
        String sql = "DELETE FROM lectures WHERE id=?;";
        jdbcTemplate.update(sql, model.getId());
    }

    @Override
    public int[] saveAll(List<Lecture> modelList) {
        String sql = "INSERT INTO lectures (id, date_time, classroom_id, teacher_id, group_id, course_id) values(DEFAULT,?,?,?,?,?);";
        List<Object[]> batch = new ArrayList<>();
        for (Lecture lecture : modelList) {
            Object[] values = new Object[]{
                    lecture.getDateTime(), lecture.getClassroom().getId(), lecture.getTeacher().getId(), lecture.getGroup().getId(), lecture.getCourse().getId()};
            batch.add(values);
        }
        return jdbcTemplate.batchUpdate(sql, batch);
    }
}
