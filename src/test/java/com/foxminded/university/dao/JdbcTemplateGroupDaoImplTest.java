package com.foxminded.university.dao;

import com.foxminded.university.config.UniversityTestConfig;
import com.foxminded.university.domain.model.Group;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(UniversityTestConfig.class)
@TestPropertySource("classpath:h2.properties")
class JdbcTemplateGroupDaoImplTest {
    public static final String GROUP_SCRIPT = "group.sql";

    @Autowired
    private DBFactory dbFactory;
    @Autowired
    private GroupDao groupDao;


    @BeforeEach
    void setUp() {
        dbFactory.createTables();
        dbFactory.executeScriptFromResources(GROUP_SCRIPT);
    }

    @AfterEach
    void tearDown() {
        dbFactory.dropTables();
    }

    @Test
    void getById_shouldReturnGroup_whenGettingGroupIsExist() {
        Group expected = new Group();
        expected.setId(2);
        expected.setName("GR-22");

        Optional<Group> actual = groupDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void getById_shouldReturnOptionalEmpty_whenGettingGroupNotExist() {
        Optional<Group> actual = groupDao.getById(111);

        assertFalse(actual.isPresent());
    }

    @Test
    void getAll_shouldReturnAllGroups() {
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
        List<Group> expected = Arrays.asList(group1, group2, group3, group4);

        List<Group> actual = groupDao.getAll();

        assertEquals(expected, actual);
    }

    @Test
    void save_shouldSaveGroup_whenSavingGroupNotExist() {
        Group expected = new Group();
        expected.setName("NN-11");

        groupDao.save(expected);
        Optional<Group> actual = groupDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldUpdateGroup_whenUpdatingGroupIsExist() {
        Group expected = new Group();
        expected.setId(1);
        expected.setName("UN-11");

        groupDao.update(expected);
        Optional<Group> actual = groupDao.getById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void update_shouldNotReturnGroup_whenUpdatingGroupNotExist() {
        Group expected = new Group();
        expected.setId(11);
        expected.setName("UN-11");

        groupDao.update(expected);
        Optional<Group> actual = groupDao.getById(expected.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void delete_shouldDeleteGroup_whenDeletingGroupIsExist() {
        Group group = new Group();
        group.setId(1);

        groupDao.delete(group);
        Optional<Group> actual = groupDao.getById(group.getId());

        assertFalse(actual.isPresent());
    }

    @Test
    void saveAll_shouldSaveAllGroups() {
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
        Group newGroup1 = new Group();
        newGroup1.setId(5);
        newGroup1.setName("NN-11");
        Group newGroup2 = new Group();
        newGroup2.setId(6);
        newGroup2.setName("NN-22");
        Group newGroup3 = new Group();
        newGroup3.setId(7);
        newGroup3.setName("NN-33");
        List<Group> savingList = Arrays.asList(newGroup1, newGroup2, newGroup3);

        List<Group> expected = Arrays.asList(group1, group2, group3, group4, newGroup1, newGroup2, newGroup3);

        groupDao.saveAll(savingList);
        List<Group> actual = groupDao.getAll();

        assertEquals(expected, actual);
    }

}
