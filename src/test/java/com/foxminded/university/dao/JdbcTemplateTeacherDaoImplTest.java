package com.foxminded.university.dao;

import com.foxminded.university.config.UniversityTestConfig;
import com.foxminded.university.domain.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(UniversityTestConfig.class)
class JdbcTemplateTeacherDaoImplTest {
    public static final String TEACHER_SCRIPT = "teacher.sql";

    @Autowired
    private DBFactory dbFactory;
    @Autowired
    private TeacherDao teacherDao;

    @BeforeEach
    void setUp() {
        dbFactory.createTables();
        dbFactory.executeScriptFromResources(TEACHER_SCRIPT);
    }

    @AfterEach
    void tearDown() {
        dbFactory.dropTables();
    }

    @Test
    void getById_shouldReturnTeacher_whenGettingTeacherIsExist() {
        Teacher expected = new Teacher();
        expected.setId(1);
        expected.setFirstName("Petr");
        expected.setLastName("Ivanov");

        Optional<Teacher> actual = teacherDao.getById(1);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void getById_shouldReturnOptionalEmpty_whenGettingTeacherNotExist() {
        Optional<Teacher> actual = teacherDao.getById(111);

        assertFalse(actual.isPresent());
    }

    @Test
    void getAll_shouldReturnAllTeachers() {
        Teacher ivanov = new Teacher();
        ivanov.setId(1);
        ivanov.setFirstName("Petr");
        ivanov.setLastName("Ivanov");
        Teacher petrov = new Teacher();
        petrov.setId(2);
        petrov.setFirstName("Sergey");
        petrov.setLastName("Petrov");
        Teacher sidorov = new Teacher();
        sidorov.setId(3);
        sidorov.setFirstName("Mike");
        sidorov.setLastName("Sidorov");
        Teacher zhidkov = new Teacher();
        zhidkov.setId(4);
        zhidkov.setFirstName("Ivan");
        zhidkov.setLastName("Zhidkov");

        List<Teacher> expected = Arrays.asList(ivanov, petrov, sidorov, zhidkov);

        List<Teacher> actual = teacherDao.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void save_shouldSaveTeacher_whenSavingTeacherNotExist() {
        Teacher expected = new Teacher();
        expected.setFirstName("NewFirstName");
        expected.setLastName("NewFirstName");

        teacherDao.save(expected);
        Optional<Teacher> actual = teacherDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldUpdateTeacher_whenUpdatingTeacherIsExist() {
        Teacher expected = new Teacher();
        expected.setId(1);
        expected.setFirstName("UpdatedFirstName");
        expected.setLastName("UpdatedFirstName");

        teacherDao.update(expected);
        Optional<Teacher> actual = teacherDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldNotReturnTeacher_whenUpdatingTeacherNotExist() {
        Teacher expected = new Teacher();
        expected.setId(5);
        expected.setFirstName("UpdatedFirstName");
        expected.setLastName("UpdatedFirstName");

        teacherDao.update(expected);
        Optional<Teacher> actual = teacherDao.getById(expected.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void delete_shouldDeleteTeacher_whenDeletingTeacherIsExist() {
        Teacher deleted = new Teacher();
        deleted.setId(1);

        teacherDao.delete(deleted);
        Optional<Teacher> actual = teacherDao.getById(deleted.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void saveAll_shouldSaveAllTeachers() {
        Teacher ivanov = new Teacher();
        ivanov.setId(1);
        ivanov.setFirstName("Petr");
        ivanov.setLastName("Ivanov");
        Teacher petrov = new Teacher();
        petrov.setId(2);
        petrov.setFirstName("Sergey");
        petrov.setLastName("Petrov");
        Teacher sidorov = new Teacher();
        sidorov.setId(3);
        sidorov.setFirstName("Mike");
        sidorov.setLastName("Sidorov");
        Teacher zhidkov = new Teacher();
        zhidkov.setId(4);
        zhidkov.setFirstName("Ivan");
        zhidkov.setLastName("Zhidkov");
        Teacher newTeacher1 = new Teacher();
        newTeacher1.setId(5);
        newTeacher1.setFirstName("newTeacher1");
        newTeacher1.setLastName("newTeacher1");
        Teacher newTeacher2 = new Teacher();
        newTeacher2.setId(6);
        newTeacher2.setFirstName("newTeacher2");
        newTeacher2.setLastName("newTeacher2");
        Teacher newTeacher3 = new Teacher();
        newTeacher3.setId(7);
        newTeacher3.setFirstName("newTeacher3");
        newTeacher3.setLastName("newTeacher3");
        List<Teacher> savingList = Arrays.asList(newTeacher1, newTeacher2, newTeacher3);


        List<Teacher> expected = Arrays.asList(ivanov, petrov, sidorov, zhidkov, newTeacher1, newTeacher2, newTeacher3);

        teacherDao.saveAll(savingList);
        List<Teacher> actual = teacherDao.getAll();

        assertEquals(expected, actual);
    }

}
