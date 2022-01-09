package com.foxminded.university.dao;

import com.foxminded.university.domain.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("squid:S106") //don't use logger in this task
public class JdbcTemplateGroupDaoImpl implements GroupDao {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertGroup;
    private final RowMapper<Group> groupRowMapper;

    @Autowired
    public JdbcTemplateGroupDaoImpl(
            JdbcTemplate jdbcTemplate,
            @Qualifier("insertGroup") SimpleJdbcInsert insertGroup,
            RowMapper<Group> groupRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertGroup = insertGroup;
        this.groupRowMapper = groupRowMapper;
    }

    @Override
    public Optional<Group> getById(Integer id) {
        String sql = "SELECT * FROM groups where id=?;";
        Group group = null;
        try {
            group = jdbcTemplate.queryForObject(sql, groupRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("FAIL getById " + id);
        }
        return Optional.ofNullable(group);
    }

    @Override
    public List<Group> getAll() {
        String sql = "SELECT * FROM groups;";
        return jdbcTemplate.query(sql, groupRowMapper);
    }

    @Override
    public void save(Group model) {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("name", model.getName());
        Number newId = insertGroup.executeAndReturnKey(parameters);
        model.setId(newId.intValue());
    }

    @Override
    public void update(Group model) {
        String sql = "UPDATE groups set name=?  WHERE id=?;";
        jdbcTemplate.update(sql, model.getName(), model.getId());
    }

    @Override
    public void delete(Group model) {
        String sql = "DELETE FROM groups WHERE id=?;";
        jdbcTemplate.update(sql, model.getId());
    }

    @Override
    public int[] saveAll(List<Group> modelList) {
        String sql = "INSERT INTO groups (id, name) values(DEFAULT,?);";

        List<Object[]> batch = new ArrayList<>();
        for (Group group : modelList) {
            Object[] values = new Object[]{
                    group.getName()};
            batch.add(values);
        }
        return jdbcTemplate.batchUpdate(sql, batch);
    }
}
