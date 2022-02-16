package com.foxminded.university.service;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.domain.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Optional<Course> getById(Integer id) {
        return courseDao.getById(id);
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public void save(Course model) {
        try {
            courseDao.save(model);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Course model) {
        courseDao.update(model);
    }

    @Override
    public void delete(Course model) {
        courseDao.delete(model);
    }

    @Override
    public int[] saveAll(List<Course> modelList) {
        int[] updateResult = {};
        try {
            updateResult = courseDao.saveAll(modelList);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return updateResult;
    }
}
