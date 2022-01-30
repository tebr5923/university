package com.foxminded.university.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Lecture {
    private int id;
    private LocalDateTime dateTime;
    private Classroom classroom;
    private Teacher teacher;
    private Group group;
    private Course course;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return id == lecture.id && Objects.equals(dateTime, lecture.dateTime) &&
                Objects.equals(classroom, lecture.classroom)&&
                Objects.equals(teacher, lecture.teacher);

       /* return id == lecture.id && Objects.equals(dateTime, lecture.dateTime) &&
                Objects.equals(classroom, lecture.classroom) &&
                Objects.equals(teacher, lecture.teacher) &&
                Objects.equals(group.getName(), lecture.group.getName()) &&
                Objects.equals(course, lecture.course);*/
    }

    @Override
    public int hashCode() {
        //return Objects.hash(id, dateTime, classroom, teacher, group.getName(), course);
        return Objects.hash(id, dateTime, classroom, teacher);
    }

    @Override
    public String toString() {
       /* return "Lecture{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", classroom=" + classroom +
                ", teacher=" + teacher +
                ", group=" + group.getName() +
                ", course=" + course +
                '}';*/
        return "Lecture{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", classroom=" + classroom +
                ", teacher=" + teacher +
                '}';
    }
}
