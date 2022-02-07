package com.foxminded.university.dao;

import com.foxminded.university.config.UniversityDaoTestConfig;
import com.foxminded.university.domain.model.Course;
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

@SpringJUnitConfig(UniversityDaoTestConfig.class)
class JdbcTemplateCourseDaoImplTest {
    public static final String COURSE_SCRIPT = "course.sql";

    @Autowired
    private DBFactory dbFactory;
    @Autowired
    private CourseDao courseDao;

    @BeforeEach
    void setUp() {
        dbFactory.createTables();
        dbFactory.executeScriptFromResources(COURSE_SCRIPT);
    }

    @AfterEach
    void tearDown() {
        dbFactory.dropTables();
    }

    @Test
    void getById_shouldReturnCourse_whenGettingCourseIsExist() {
        Course expected = new Course();
        expected.setId(2);
        expected.setName("math");

        Optional<Course> actual = courseDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void getById_shouldReturnOptionalEmpty_whenGettingCourseNotExist() {
        Optional<Course> actual = courseDao.getById(111);

        assertFalse(actual.isPresent());
    }

    @Test
    void getAll_shouldReturnAllCourses() {
        Course history = new Course();
        history.setId(1);
        history.setName("history");
        Course math = new Course();
        math.setId(2);
        math.setName("math");
        Course english = new Course();
        english.setId(3);
        english.setName("english");
        Course literature = new Course();
        literature.setId(4);
        literature.setName("literature");
        List<Course> expected = Arrays.asList(history, math, english, literature);

        List<Course> actual = courseDao.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void save_shouldSaveCourse_whenSavingCourseNotExist() {
        Course expected = new Course();
        expected.setName("new course");

        courseDao.save(expected);
        Optional<Course> actual = courseDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldUpdateCourse_whenUpdatingCourseIsExist() {
        Course expected = new Course();
        expected.setId(1);
        expected.setName("updated");

        courseDao.update(expected);
        Optional<Course> actual = courseDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldNotReturnCourse_whenUpdatingCourseNotExist() {
        Course expected = new Course();
        expected.setId(11);
        expected.setName("updated");

        courseDao.update(expected);
        Optional<Course> actual = courseDao.getById(expected.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void delete_shouldDeleteCourse_whenDeletingCourseIsExist() {
        Course course = new Course();
        course.setId(1);

        courseDao.delete(course);
        Optional<Course> actual = courseDao.getById(course.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void saveAll_shouldSaveAllCourse() {
        Course history = new Course();
        history.setId(1);
        history.setName("history");
        Course math = new Course();
        math.setId(2);
        math.setName("math");
        Course english = new Course();
        english.setId(3);
        english.setName("english");
        Course literature = new Course();
        literature.setId(4);
        literature.setName("literature");
        Course newCourse1 = new Course();
        newCourse1.setId(5);
        newCourse1.setName("new course5");
        Course newCourse2 = new Course();
        newCourse2.setId(6);
        newCourse2.setName("new course6");
        Course newCourse3 = new Course();
        newCourse3.setId(7);
        newCourse3.setName("new course7");

        List<Course> savingList = Arrays.asList(newCourse1, newCourse2, newCourse3);

        List<Course> expected = Arrays.asList(history, math, english, literature, newCourse1, newCourse2, newCourse3);

        courseDao.saveAll(savingList);
        List<Course> actual = courseDao.getAll();

        assertEquals(expected, actual);
    }

}