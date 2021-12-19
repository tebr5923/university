package com.foxminded.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Lecture {
    private int id;
    private LocalDateTime dateTime;
    private Classroom classroom;
    private Teacher teacher;
    private Group group;
    private Course course;

    public Lecture() {
        //default constructor
    }

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
        return Objects.equals(dateTime, lecture.dateTime) &&
                Objects.equals(classroom, lecture.classroom) &&
                Objects.equals(teacher, lecture.teacher) &&
                Objects.equals(group, lecture.group) &&
                Objects.equals(course, lecture.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, classroom, teacher, group, course);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "dateTime=" + dateTime +
                ", classroom=" + classroom +
                ", teacher=" + teacher +
                ", group=" + group +
                ", course=" + course +
                '}';
    }
}
