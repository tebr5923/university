package com.foxminded.university.service;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.domain.model.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseDao mockCourseDao;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void getById_shouldCallGetByIdFromDao_whenCallGetByIdFromService() {
        courseService.getById(1);
        verify(mockCourseDao, times(1)).getById(1);
    }

    @Test
    void getAll_shouldCallGetAllFromDao_whenCallGetAllFromService() {
        courseService.getAll();
        verify(mockCourseDao, times(1)).getAll();
    }

    @Test
    void save_shouldCallSaveFromDao_whenCallSaveFromService() throws DaoException {
        Course course = new Course();
        course.setName("course");
        courseService.save(course);
        verify(mockCourseDao, times(1)).save(course);
    }

    @Test
    void update_shouldCallUpdateFromDao_whenCallUpdateFromService() {
        Course course = new Course();
        course.setName("course");
        courseService.update(course);
        verify(mockCourseDao, times(1)).update(course);
    }

    @Test
    void delete_shouldCallDeleteFromDao_whenCallDeleteFromService() {
        Course course = new Course();
        course.setName("course");
        courseService.delete(course);
        verify(mockCourseDao, times(1)).delete(course);
    }

    @Test
    void saveAll_shouldCallSaveAllFromDao_whenCallSaveAllFromService() throws DaoException {
        Course course = new Course();
        course.setName("course");
        Course math = new Course();
        math.setName("math");
        List<Course> courses = Arrays.asList(course, math);
        courseService.saveAll(courses);
        verify(mockCourseDao, times(1)).saveAll(courses);
    }

}
