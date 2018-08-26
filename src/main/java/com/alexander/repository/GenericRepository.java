package com.alexander.repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository<T, ID> {
    T create(T t);

    void delete(ID id);

    T getById(ID id) throws IOException;

    List<T> getAll() throws IOException;

    void clearAll();

}
