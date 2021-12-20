package com.foxminded.domain.timetable;

import com.foxminded.domain.model.Lecture;

import java.util.List;

public interface Timetable {
    List<Lecture> getLectures();
}
