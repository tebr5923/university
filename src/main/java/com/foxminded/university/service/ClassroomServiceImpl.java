package com.foxminded.university.service;

import com.foxminded.university.dao.ClassroomDao;
import com.foxminded.university.domain.model.Classroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    private final ClassroomDao classroomDao;

    @Autowired
    public ClassroomServiceImpl(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    @Override
    public Optional<Classroom> getById(Integer id) {
        return classroomDao.getById(id);
    }

    @Override
    public List<Classroom> getAll() {
        return classroomDao.getAll();
    }

    @Override
    public void save(Classroom model) {
        classroomDao.save(model);
    }

    @Override
    public void update(Classroom model) {
        classroomDao.update(model);
    }

    @Override
    public void delete(Classroom model) {
        classroomDao.delete(model);
    }

    @Override
    public int[] saveAll(List<Classroom> modelList) {
        return classroomDao.saveAll(modelList);
    }

}
