package com.pizza.pizza.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    public List<T> findAll();
    public Optional<T> findById(ID id);
    public T save(T t);
    public void deleteById(ID id);
}
