package com.foxminded.university.service;

import com.foxminded.university.dao.ClassroomDao;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.domain.model.Classroom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ClassroomServiceImplTest {

    @Mock
    private ClassroomDao mockClassroomDao;

    @InjectMocks
    private ClassroomServiceImpl classroomService;

    @Test
    void getById_shouldCallGetByIdFromDao_whenCallGetByIdFromService() {
        classroomService.getById(1);
        verify(mockClassroomDao, times(1)).getById(1);
    }

    @Test
    void getAll_shouldCallGetAllFromDao_whenCallGetAllFromService() {
        classroomService.getAll();
        verify(mockClassroomDao, times(1)).getAll();
    }

    @Test
    void save_shouldCallSaveFromDao_whenCallSaveFromService() throws DaoException {
        Classroom classroom1 = new Classroom();
        classroom1.setNumber(11);
        classroomService.save(classroom1);
        verify(mockClassroomDao, times(1)).save(classroom1);
    }

    @Test
    void update_shouldCallUpdateFromDao_whenCallUpdateFromService()  {
        Classroom classroom1 = new Classroom();
        classroom1.setNumber(11);
        classroomService.update(classroom1);
        verify(mockClassroomDao, times(1)).update(classroom1);
    }

    @Test
    void delete_shouldCallDeleteFromDao_whenCallDeleteFromService()  {
        Classroom classroom1 = new Classroom();
        classroom1.setNumber(11);
        classroomService.delete(classroom1);
        verify(mockClassroomDao, times(1)).delete(classroom1);
    }

    @Test
    void saveAll_shouldCallSaveAllFromDao_whenCallSaveAllFromService() throws DaoException {
        Classroom classroom1 = new Classroom();
        classroom1.setNumber(11);
        Classroom classroom2 = new Classroom();
        classroom2.setNumber(22);
        List<Classroom> classrooms = Arrays.asList(classroom1, classroom2);
        classroomService.saveAll(classrooms);
        verify(mockClassroomDao, times(1)).saveAll(classrooms);
    }

}