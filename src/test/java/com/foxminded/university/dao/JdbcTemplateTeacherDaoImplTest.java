package com.foxminded.university.dao;

import com.foxminded.university.config.UniversityConfigTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(UniversityConfigTest.class)
class JdbcTemplateTeacherDaoImplTest {
    public static final String TEACHER_SCRIPT = "teacher.sql";

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DBFactory dbFactory;

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
    void getById() {
        JdbcTemplateTeacherDaoImpl teacherDao =
                applicationContext.getBean(JdbcTemplateTeacherDaoImpl.class);
        System.out.println(teacherDao.getAll());
    }
}