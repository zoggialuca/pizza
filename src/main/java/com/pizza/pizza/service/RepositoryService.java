package com.pizza.pizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class RepositoryService<T, ID, R extends JpaRepository<T, ID>> implements BaseService<T, ID> {

    @Autowired protected R jpaRepository;

    @Override
    public List<T> findAll(){
        return jpaRepository.findAll();
    }

    @Override
    public Optional<T> findById(ID id){
        return jpaRepository.findById(id);
    }

    @Override
    public T save(T t){
        return jpaRepository.save(t);
    }

    @Override
    public void deleteById(ID id){
        jpaRepository.deleteById(id);
    }
}
