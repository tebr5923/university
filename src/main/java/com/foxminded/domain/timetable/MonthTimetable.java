package com.foxminded.domain.timetable;

import com.foxminded.domain.model.Lecture;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MonthTimetable implements Timetable {
    private Month month;
    private List<DayTimetable> dayTimetables;

    //init in constructor or in setter?
    public MonthTimetable(Month month, List<DayTimetable> dayTimetables) {
        this.month = month;
        this.dayTimetables = dayTimetables;
    }

    public Month getMonth() {
        return month;
    }

    public List<DayTimetable> getDayTimetables() {
        return dayTimetables;
    }

    @Override
    public List<Lecture> getTimetable() {
        List<Lecture> lectures = new ArrayList<>();
        dayTimetables.stream().map(DayTimetable::getTimetable).forEach(lectures::addAll);
        return lectures;
    }
}
