package com.foxminded.university.service;

import java.util.List;
import java.util.Optional;

public interface Service<T, K> {
    Optional<T> getById(K id);

    List<T> getAll();

    void save(T model);

    void update(T model);

    void delete(T model);

    int[] saveAll(List<T> modelList);
}
