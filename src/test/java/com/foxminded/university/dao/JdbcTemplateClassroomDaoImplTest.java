package com.foxminded.university.dao;

import com.foxminded.university.config.UniversityDaoTestConfig;
import com.foxminded.university.domain.model.Classroom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(UniversityDaoTestConfig.class)
class JdbcTemplateClassroomDaoImplTest {
    public static final String CLASSROOM_SCRIPT = "classroom.sql";

    @Autowired
    private DBFactory dbFactory;
    @Autowired
    private ClassroomDao classroomDao;

    @BeforeEach
    void setUp() {
        dbFactory.createTables();
        dbFactory.executeScriptFromResources(CLASSROOM_SCRIPT);
    }

    @AfterEach
    void tearDown() {
        dbFactory.dropTables();
    }

    @Test
    void getById_shouldReturnClassroom_whenGettingClassroomIsExist() {
        Classroom expected = new Classroom();
        expected.setId(2);
        expected.setNumber(22);

        Optional<Classroom> actual = classroomDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void getById_shouldReturnOptionalEmpty_whenGettingClassroomNotExist() {
        Optional<Classroom> actual = classroomDao.getById(111);

        assertFalse(actual.isPresent());
    }

    @Test
    void getAll_shouldReturnAllClassrooms() {
        Classroom classroom1 = new Classroom();
        classroom1.setId(1);
        classroom1.setNumber(11);
        Classroom classroom2 = new Classroom();
        classroom2.setId(2);
        classroom2.setNumber(22);
        Classroom classroom3 = new Classroom();
        classroom3.setId(3);
        classroom3.setNumber(33);
        Classroom classroom4 = new Classroom();
        classroom4.setId(4);
        classroom4.setNumber(44);

        List<Classroom> expected = Arrays.asList(classroom1, classroom2, classroom3, classroom4);

        List<Classroom> actual = classroomDao.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void save_shouldSaveClassroom_whenSavingClassroomNotExist() {
        Classroom expected = new Classroom();
        expected.setNumber(555);

        classroomDao.save(expected);
        Optional<Classroom> actual = classroomDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldUpdateClassroom_whenUpdatingClassroomIsExist() {
        Classroom expected = new Classroom();
        expected.setId(1);
        expected.setNumber(555);

        classroomDao.update(expected);
        Optional<Classroom> actual = classroomDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldNotReturnClassroom_whenUpdatingClassroomNotExist() {
        Classroom expected = new Classroom();
        expected.setId(11);
        expected.setNumber(555);

        classroomDao.update(expected);
        Optional<Classroom> actual = classroomDao.getById(expected.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void delete_shouldDeleteClassroom_whenDeletingClassroomIsExist() {
        Classroom classroom = new Classroom();
        classroom.setId(1);

        classroomDao.delete(classroom);
        Optional<Classroom> actual = classroomDao.getById(classroom.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void saveAll_shouldSaveAllClassrooms() {
        Classroom classroom1 = new Classroom();
        classroom1.setId(1);
        classroom1.setNumber(11);
        Classroom classroom2 = new Classroom();
        classroom2.setId(2);
        classroom2.setNumber(22);
        Classroom classroom3 = new Classroom();
        classroom3.setId(3);
        classroom3.setNumber(33);
        Classroom classroom4 = new Classroom();
        classroom4.setId(4);
        classroom4.setNumber(44);
        Classroom newClassroom1 = new Classroom();
        newClassroom1.setId(5);
        newClassroom1.setNumber(55);
        Classroom newClassroom2 = new Classroom();
        newClassroom2.setId(6);
        newClassroom2.setNumber(66);
        Classroom newClassroom3 = new Classroom();
        newClassroom3.setId(7);
        newClassroom3.setNumber(77);
        List<Classroom> savingList = Arrays.asList(newClassroom1, newClassroom2, newClassroom3);

        List<Classroom> expected = Arrays.asList(classroom1, classroom2, classroom3, classroom4, newClassroom1, newClassroom2, newClassroom3);

        classroomDao.saveAll(savingList);
        List<Classroom> actual = classroomDao.getAll();

        assertEquals(expected, actual);
    }

}