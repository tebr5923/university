package com.foxminded.university.dao;

import com.foxminded.university.config.UniversityTestConfig;
import com.foxminded.university.domain.model.Classroom;
import com.foxminded.university.domain.model.Course;
import com.foxminded.university.domain.model.Group;
import com.foxminded.university.domain.model.Lecture;
import com.foxminded.university.domain.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(UniversityTestConfig.class)
class JdbcTemplateLectureDaoImplTest {
    public static final String LECTURE_SCRIPT = "lecture.sql";

    @Autowired
    private DBFactory dbFactory;
    @Autowired
    private LectureDao lectureDao;

    @BeforeEach
    void setUp() {
        dbFactory.createTables();
        dbFactory.executeScriptFromResources(LECTURE_SCRIPT);
    }

    @AfterEach
    void tearDown() {
        dbFactory.dropTables();
    }


    @Test
    void getById_shouldReturnLecture_whenGettingLectureIsExist() {
        Lecture expected = new Lecture();
        expected.setId(1);
        LocalDateTime dateTime = LocalDateTime.of(2021, 9, 1, 8, 0, 0, 0);
        expected.setDateTime(dateTime);
        expected.setClassroom(fillClassrooms().get(0));
        expected.setTeacher(fillTeachers().get(0));
        expected.setGroup(fillGroups().get(0));
        expected.setCourse(fillCourses().get(0));

        Optional<Lecture> actual = lectureDao.getById(1);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void getById_shouldReturnOptionalEmpty_whenGettingLectureNotExist() {
        Optional<Lecture> actual = lectureDao.getById(111);

        assertFalse(actual.isPresent());
    }

    @Test
    void getAll_shouldReturnAllLectures() {
        List<Lecture> expected = fillLecturesForGetAll();

        List<Lecture> actual = lectureDao.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void save_shouldSaveLecture_whenSavingLectureNotExist() {
        Lecture expected = new Lecture();
        expected.setDateTime(LocalDateTime.of(2021, 9, 1, 16, 0, 0, 0));
        expected.setClassroom(fillClassrooms().get(0));
        expected.setTeacher(fillTeachers().get(1));
        expected.setGroup(fillGroups().get(2));
        expected.setCourse(fillCourses().get(3));

        lectureDao.save(expected);
        Optional<Lecture> actual = lectureDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldUpdateLecture_whenUpdatingLectureIsExist() {
        Lecture expected = new Lecture();
        expected.setId(1);
        expected.setDateTime(LocalDateTime.of(2021, 9, 1, 16, 0, 0, 0));
        expected.setClassroom(fillClassrooms().get(0));
        expected.setTeacher(fillTeachers().get(1));
        expected.setGroup(fillGroups().get(2));
        expected.setCourse(fillCourses().get(3));

        lectureDao.update(expected);
        Optional<Lecture> actual = lectureDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldNotReturnLecture_whenUpdatingLectureNotExist() {
        Lecture expected = new Lecture();
        expected.setId(5);
        expected.setDateTime(LocalDateTime.of(2021, 9, 1, 16, 0, 0, 0));
        expected.setClassroom(fillClassrooms().get(0));
        expected.setTeacher(fillTeachers().get(1));
        expected.setGroup(fillGroups().get(2));
        expected.setCourse(fillCourses().get(3));

        lectureDao.update(expected);
        Optional<Lecture> actual = lectureDao.getById(expected.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void delete_shouldDeleteLecture_whenDeletingLectureIsExist() {
        Lecture deleted = new Lecture();
        deleted.setId(1);

        lectureDao.delete(deleted);
        Optional<Lecture> actual = lectureDao.getById(deleted.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void saveAll_shouldSaveAllLectures() {
        List<Lecture> savingList = fillLecturesForSaveAll();
        List<Lecture> expected = new ArrayList<>(fillLecturesForGetAll());
        expected.addAll(savingList);

        lectureDao.saveAll(savingList);
        List<Lecture> actual = lectureDao.getAll();

        assertEquals(expected, actual);
    }

    private List<Lecture> fillLecturesForSaveAll() {
        Lecture lecture1 = new Lecture();
        lecture1.setId(5);
        lecture1.setDateTime(LocalDateTime.of(2021, 9, 1, 8, 0, 0, 0));
        lecture1.setClassroom(fillClassrooms().get(0));
        lecture1.setTeacher(fillTeachers().get(1));
        lecture1.setGroup(fillGroups().get(2));
        lecture1.setCourse(fillCourses().get(3));

        Lecture lecture2 = new Lecture();
        lecture2.setId(6);
        lecture2.setDateTime(LocalDateTime.of(2021, 9, 1, 10, 0, 0, 0));
        lecture2.setClassroom(fillClassrooms().get(1));
        lecture2.setTeacher(fillTeachers().get(2));
        lecture2.setGroup(fillGroups().get(3));
        lecture2.setCourse(fillCourses().get(0));

        Lecture lecture3 = new Lecture();
        lecture3.setId(7);
        lecture3.setDateTime(LocalDateTime.of(2021, 9, 1, 12, 0, 0, 0));
        lecture3.setClassroom(fillClassrooms().get(2));
        lecture3.setTeacher(fillTeachers().get(3));
        lecture3.setGroup(fillGroups().get(0));
        lecture3.setCourse(fillCourses().get(1));

        return Arrays.asList(lecture1, lecture2, lecture3);
    }

    private List<Lecture> fillLecturesForGetAll() {
        Lecture lecture1 = new Lecture();
        lecture1.setId(1);
        lecture1.setDateTime(LocalDateTime.of(2021, 9, 1, 8, 0, 0, 0));
        lecture1.setClassroom(fillClassrooms().get(0));
        lecture1.setTeacher(fillTeachers().get(0));
        lecture1.setGroup(fillGroups().get(0));
        lecture1.setCourse(fillCourses().get(0));

        Lecture lecture2 = new Lecture();
        lecture2.setId(2);
        lecture2.setDateTime(LocalDateTime.of(2021, 9, 1, 10, 0, 0, 0));
        lecture2.setClassroom(fillClassrooms().get(1));
        lecture2.setTeacher(fillTeachers().get(1));
        lecture2.setGroup(fillGroups().get(1));
        lecture2.setCourse(fillCourses().get(1));

        Lecture lecture3 = new Lecture();
        lecture3.setId(3);
        lecture3.setDateTime(LocalDateTime.of(2021, 9, 1, 12, 0, 0, 0));
        lecture3.setClassroom(fillClassrooms().get(2));
        lecture3.setTeacher(fillTeachers().get(2));
        lecture3.setGroup(fillGroups().get(2));
        lecture3.setCourse(fillCourses().get(2));

        Lecture lecture4 = new Lecture();
        lecture4.setId(4);
        lecture4.setDateTime(LocalDateTime.of(2021, 9, 1, 14, 0, 0, 0));
        lecture4.setClassroom(fillClassrooms().get(3));
        lecture4.setTeacher(fillTeachers().get(3));
        lecture4.setGroup(fillGroups().get(3));
        lecture4.setCourse(fillCourses().get(3));

        return Arrays.asList(lecture1, lecture2, lecture3, lecture4);
    }

    private List<Course> fillCourses() {
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

        return Arrays.asList(history, math, english, literature);
    }

    private List<Group> fillGroups() {
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

        return Arrays.asList(group1, group2, group3, group4);
    }

    private List<Classroom> fillClassrooms() {
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

        return Arrays.asList(classroom1, classroom2, classroom3, classroom4);
    }

    private List<Teacher> fillTeachers() {
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

        return Arrays.asList(ivanov, petrov, sidorov, zhidkov);
    }

}
