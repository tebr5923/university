package com.foxminded.university.domain.timetable;

import com.foxminded.university.domain.model.Lecture;

import java.util.List;

public interface Timetable {
    List<Lecture> getLectures();
}
