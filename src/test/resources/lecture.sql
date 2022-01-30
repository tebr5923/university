INSERT INTO classrooms (number) VALUES (11);
INSERT INTO classrooms (number) VALUES (22);
INSERT INTO classrooms (number) VALUES (33);
INSERT INTO classrooms (number) VALUES (44);

insert into teachers (first_name, last_name) VALUES ('Petr', 'Ivanov');
insert into teachers (first_name, last_name) VALUES ('Sergey', 'Petrov');
insert into teachers (first_name, last_name) VALUES ('Mike', 'Sidorov');
insert into teachers (first_name, last_name) VALUES ('Ivan', 'Zhidkov');

INSERT INTO groups (name) VALUES ('GR-11');
INSERT INTO groups (name) VALUES ('GR-22');
INSERT INTO groups (name) VALUES ('GR-33');
INSERT INTO groups (name) VALUES ('GR-44');

INSERT INTO courses (name) VALUES ('history');
INSERT INTO courses (name) VALUES ('math');
INSERT INTO courses (name) VALUES ('english');
INSERT INTO courses (name) VALUES ('literature');

INSERT INTO lectures (date_time, classroom_id, teacher_id, group_id, course_id)
VALUES ('2021-09-01 08:00:00', 1, 1, 1, 1);
INSERT INTO lectures (date_time, classroom_id, teacher_id, group_id, course_id)
VALUES ('2021-09-01 10:00:00', 2, 2, 2, 2);
INSERT INTO lectures (date_time, classroom_id, teacher_id, group_id, course_id)
VALUES ('2021-09-01 12:00:00', 3, 3, 3, 3);
INSERT INTO lectures (date_time, classroom_id, teacher_id, group_id, course_id)
VALUES ('2021-09-01 14:00:00', 4, 4, 4, 4);
