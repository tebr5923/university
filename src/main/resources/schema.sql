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
    id         int         NOT NULL PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name  varchar(50) NOT NULL
);


ALTER TABLE teachers
    ALTER COLUMN id
        SET DEFAULT NEXTVAL('teachers_id_seq');


CREATE SEQUENCE groups_id_seq START 1;

CREATE TABLE groups
(
    id   int        NOT NULL PRIMARY KEY,
    name varchar(5) NOT NULL
);

ALTER TABLE groups
    ALTER COLUMN id
        SET DEFAULT NEXTVAL('groups_id_seq');


CREATE SEQUENCE students_id_seq START 1;

CREATE TABLE students
(
    id         int         NOT NULL PRIMARY KEY,
    group_id   int,
    first_name varchar(50) NOT NULL,
    last_name  varchar(50) NOT NULL,
    FOREIGN KEY (group_id)
        REFERENCES groups (id)
        ON DELETE CASCADE
);

ALTER TABLE students
    ALTER COLUMN id
        SET DEFAULT NEXTVAL('students_id_seq');



CREATE SEQUENCE courses_id_seq START 1;

CREATE TABLE courses
(
    id   int         NOT NULL PRIMARY KEY,
    name varchar(50) NOT NULL
);

ALTER TABLE courses
    ALTER COLUMN id
        SET DEFAULT NEXTVAL('courses_id_seq');


CREATE SEQUENCE classrooms_id_seq START 1;

CREATE TABLE classrooms
(
    id     int NOT NULL PRIMARY KEY,
    number int NOT NULL
);

ALTER TABLE classrooms
    ALTER COLUMN id
        SET DEFAULT NEXTVAL('classrooms_id_seq');


CREATE SEQUENCE lectures_id_seq START 1;

CREATE TABLE lectures
(
    id           int NOT NULL PRIMARY KEY,
    date_time timestamptz,
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

ALTER TABLE lectures
    ALTER COLUMN id
        SET DEFAULT NEXTVAL('lectures_id_seq');

