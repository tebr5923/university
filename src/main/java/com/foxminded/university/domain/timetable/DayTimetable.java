package com.foxminded.university.domain.timetable;

import com.foxminded.university.domain.model.Lecture;

import java.util.List;
import java.util.Objects;

public class DayTimetable implements Timetable {
    private final int dayOfMonth;
    private final List<Lecture> lectures;

    public DayTimetable(int dayOfMonth, List<Lecture> lectures) {
        this.dayOfMonth = dayOfMonth;
        this.lectures = lectures;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    @Override
    public List<Lecture> getLectures() {
        return lectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayTimetable that = (DayTimetable) o;
        return dayOfMonth == that.dayOfMonth && Objects.equals(lectures, that.lectures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfMonth, lectures);
    }
}
