package com.foxminded.domain.timetable;

import com.foxminded.domain.model.Lecture;

import java.util.List;

public class DayTimetable implements Timetable {
    private int dayOfMonth;
    private List<Lecture> lectures;

    //init in constructor or in setter?
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
