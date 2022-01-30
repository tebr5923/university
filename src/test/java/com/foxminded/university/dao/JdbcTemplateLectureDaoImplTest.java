package com.foxminded.university.dao;

import com.foxminded.university.config.UniversityTestConfig;
import com.foxminded.university.domain.model.Classroom;
import com.foxminded.university.domain.model.Lecture;
import com.foxminded.university.domain.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Classroom classroom = new Classroom();
        classroom.setId(1);
        classroom.setNumber(11);
        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setFirstName("Petr");
        teacher.setLastName("Ivanov");
        Lecture expected = new Lecture();
        expected.setId(1);
        LocalDateTime dateTime = LocalDateTime.of(2021, 9, 1, 8, 0, 0, 0);
        expected.setDateTime(dateTime);
        expected.setClassroom(classroom);
        expected.setTeacher(teacher);

        Optional<Lecture> actual = lectureDao.getById(1);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

}
