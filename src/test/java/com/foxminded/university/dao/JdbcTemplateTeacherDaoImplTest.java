package com.foxminded.university.dao;

import com.foxminded.university.config.UniversityConfigTest;
import com.foxminded.university.domain.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(UniversityConfigTest.class)
@TestPropertySource("classpath:h2.properties")
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
    void getById_shouldReturnTeacher_whenGetTeacherWhichExist() {
        Teacher expected = new Teacher();
        expected.setId(1);
        expected.setFirstName("Petr");
        expected.setLastName("Ivanov");

        Optional<Teacher> actual = teacherDao.getById(1);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void getById_shouldReturnOptionalEmpty_whenGetTeacherNotExist() {
        Optional<Teacher> expected = Optional.empty();

        Optional<Teacher> actual = teacherDao.getById(111);

        assertFalse(actual.isPresent());
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
}
