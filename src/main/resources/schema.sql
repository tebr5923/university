DROP TABLE IF EXISTS groups_courses;
DROP TABLE IF EXISTS lectures;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS classrooms;
DROP SEQUENCE IF EXISTS teachers_id_seq;
DROP SEQUENCE IF EXISTS students_id_seq;
DROP SEQUENCE IF EXISTS groups_id_seq;
DROP SEQUENCE IF EXISTS courses_id_seq;
DROP SEQUENCE IF EXISTS classrooms_id_seq;
DROP SEQUENCE IF EXISTS lectures_id_seq;

CREATE SEQUENCE teachers_id_seq START 1;

CREATE TABLE teachers
(
    id         SERIAL PRIMARY KEY,
    first_name varchar(50)                            NOT NULL,
    last_name  varchar(50)                            NOT NULL
);


CREATE SEQUENCE groups_id_seq START 1;

CREATE TABLE groups
(
    id   int DEFAULT NEXTVAL('groups_id_seq') NOT NULL PRIMARY KEY,
    name varchar(5)                           NOT NULL
);


CREATE SEQUENCE students_id_seq START 1;

CREATE TABLE students
(
    id         int DEFAULT NEXTVAL('students_id_seq') NOT NULL PRIMARY KEY,
    group_id   int,
    first_name varchar(50)                            NOT NULL,
    last_name  varchar(50)                            NOT NULL,
    FOREIGN KEY (group_id)
        REFERENCES groups (id)
        ON DELETE CASCADE
);


CREATE SEQUENCE courses_id_seq START 1;

CREATE TABLE courses
(
    id   int DEFAULT NEXTVAL('courses_id_seq') NOT NULL PRIMARY KEY,
    name varchar(50)                           NOT NULL
);


CREATE SEQUENCE classrooms_id_seq START 1;

CREATE TABLE classrooms
(
    id     int DEFAULT NEXTVAL('classrooms_id_seq') NOT NULL PRIMARY KEY,
    number int                                      NOT NULL
);


CREATE SEQUENCE lectures_id_seq START 1;

CREATE TABLE lectures
(
    id           int DEFAULT NEXTVAL('lectures_id_seq') NOT NULL PRIMARY KEY,
    date_time    timestamptz,
    classroom_id int,
    teacher_id   int,
    group_id     int,
    course_id    int,
    FOREIGN KEY (classroom_id)
        REFERENCES classrooms (id)
        ON DELETE CASCADE,
    FOREIGN KEY (teacher_id)
        REFERENCES teachers (id)
        ON DELETE CASCADE,
    FOREIGN KEY (group_id)
        REFERENCES groups (id)
        ON DELETE CASCADE,
    FOREIGN KEY (course_id)
        REFERENCES courses (id)
        ON DELETE CASCADE
);


CREATE TABLE groups_courses
(
    group_id  int NOT NULL,
    course_id int NOT NULL,
    PRIMARY KEY (group_id, course_id),
    FOREIGN KEY (group_id)
        REFERENCES groups (id)
        ON DELETE CASCADE,
    FOREIGN KEY (course_id)
        REFERENCES courses (id)
        ON DELETE CASCADE
);
