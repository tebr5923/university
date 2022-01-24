package com.foxminded.university.dao;

import com.foxminded.university.config.UniversityTestConfig;
import com.foxminded.university.domain.model.Group;
import com.foxminded.university.domain.model.Student;
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
class JdbcTemplateStudentDaoImplTest {
    public static final String STUDENT_SCRIPT = "student.sql";

    @Autowired
    private DBFactory dbFactory;
    @Autowired
    private StudentDao studentDao;

    @BeforeEach
    void setUp() {
        dbFactory.createTables();
        dbFactory.executeScriptFromResources(STUDENT_SCRIPT);
    }

    @AfterEach
    void tearDown() {
        dbFactory.dropTables();
    }

    @Test
    void getById_shouldReturnStudent_whenGettingStudentIsExist() {
        Group group1 = new Group();
        group1.setId(1);
        group1.setName("GR-11");
        Student expected = new Student();
        expected.setId(1);
        expected.setFirstName("Ivan");
        expected.setLastName("Ivanov");
        expected.setGroup(group1);

        Optional<Student> actual = studentDao.getById(1);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void getById_shouldReturnOptionalEmpty_whenGettingStudentNotExist() {
        Optional<Student> actual = studentDao.getById(111);

        assertFalse(actual.isPresent());
    }

    @Test
    void getAll_shouldReturnAllStudents() {
        Group group1 = new Group();
        group1.setId(1);
        group1.setName("GR-11");
        Group group2 = new Group();
        group2.setId(2);
        group2.setName("GR-22");
        Group group3 = new Group();
        group3.setId(3);
        group3.setName("GR-33");
        Group group4 = new Group();
        group4.setId(4);
        group4.setName("GR-44");
        Student student1 = new Student();
        student1.setId(1);
        student1.setFirstName("Ivan");
        student1.setLastName("Ivanov");
        student1.setGroup(group1);
        Student student2 = new Student();
        student2.setId(2);
        student2.setFirstName("Petr");
        student2.setLastName("Petrov");
        student2.setGroup(group2);
        Student student3 = new Student();
        student3.setId(3);
        student3.setFirstName("Pavel");
        student3.setLastName("Pavlov");
        student3.setGroup(group3);
        Student student4 = new Student();
        student4.setId(4);
        student4.setFirstName("Denis");
        student4.setLastName("Denisov");
        student4.setGroup(group4);
        List<Student> expected = Arrays.asList(student1, student2, student3, student4);

        List<Student> actual = studentDao.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void save_shouldSaveStudent_whenSavingStudentNotExist() {
        Group group1 = new Group();
        group1.setId(1);
        group1.setName("GR-11");
        Student expected = new Student();
        expected.setGroup(group1);
        expected.setFirstName("NewFirstName");
        expected.setLastName("NewLastName");

        studentDao.save(expected);
        Optional<Student> actual = studentDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldUpdateStudent_whenUpdatingStudentIsExist() {
        Group group2 = new Group();
        group2.setId(2);
        group2.setName("GR-22");
        Student expected = new Student();
        expected.setId(1);
        expected.setGroup(group2);
        expected.setFirstName("UpdatedFirstName");
        expected.setLastName("UpdatedFirstName");

        studentDao.update(expected);
        Optional<Student> actual = studentDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldNotReturnStudent_whenUpdatingStudentNotExist() {
        Group group2 = new Group();
        group2.setId(2);
        group2.setName("GR-22");
        Student expected = new Student();
        expected.setId(5);
        expected.setGroup(group2);
        expected.setFirstName("UpdatedFirstName");
        expected.setLastName("UpdatedFirstName");

        studentDao.update(expected);
        Optional<Student> actual = studentDao.getById(expected.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void delete_shouldDeleteStudent_whenDeletingStudentIsExist() {
        Student deleted = new Student();
        deleted.setId(1);

        studentDao.delete(deleted);
        Optional<Student> actual = studentDao.getById(deleted.getId());

        assertFalse(actual.isPresent());
    }

}