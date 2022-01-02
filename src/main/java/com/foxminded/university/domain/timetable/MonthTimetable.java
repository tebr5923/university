package com.foxminded.university.domain.timetable;

import com.foxminded.university.domain.model.Lecture;

import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MonthTimetable implements Timetable {
    private final Month month;
    private final List<DayTimetable> dayTimetables;

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
    public List<Lecture> getLectures() {
        return dayTimetables.stream()
                .map(DayTimetable::getLectures)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthTimetable that = (MonthTimetable) o;
        return month == that.month && Objects.equals(dayTimetables, that.dayTimetables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, dayTimetables);
    }
}
