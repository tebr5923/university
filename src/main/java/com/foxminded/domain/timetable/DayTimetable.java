package com.foxminded.domain.timetable;

import com.foxminded.domain.model.Lecture;

import java.util.List;

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
    public List<Lecture> getTimetable() {
        return lectures;
    }
}
