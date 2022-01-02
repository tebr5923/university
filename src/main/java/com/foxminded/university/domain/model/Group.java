package com.foxminded.university.domain.model;

import com.foxminded.university.domain.timetable.MonthTimetable;

import java.util.List;
import java.util.Objects;

public class Group {
    private int id;
    private String name;
    private List<MonthTimetable> monthTimetables;
    private List<Course> courses;
    private List<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MonthTimetable> getMonthTimetables() {
        return monthTimetables;
    }

    public void setMonthTimetables(List<MonthTimetable> monthTimetables) {
        this.monthTimetables = monthTimetables;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id &&
                Objects.equals(name, group.name) &&
                Objects.equals(monthTimetables, group.monthTimetables) &&
                Objects.equals(courses, group.courses) &&
                Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, monthTimetables, courses, students);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
