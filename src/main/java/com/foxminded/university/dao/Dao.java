package com.foxminded.university.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, K> {
    Optional<T> getById(K id);

    List<T> getAll();

    void save(T model);

    void update(T model);

    void delete(T model);

    void saveAll(List<T> modelList);
}
