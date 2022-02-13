package com.foxminded.university.service;

import com.foxminded.university.domain.model.Classroom;

import java.util.Optional;

public interface ClassroomService {
    Optional<Classroom> getById(Integer id);

}
