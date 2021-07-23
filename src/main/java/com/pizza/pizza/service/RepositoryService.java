package com.pizza.pizza.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public class RepositoryService<T, ID> implements BaseService<T, ID> {

    protected JpaRepository<T, ID> jpaRepository;

    public RepositoryService(JpaRepository<T, ID> jpaRepository){
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<T> findAll(){
        return jpaRepository.findAll();
    }

    @Override
    public Optional<T> findById(ID id){
        return jpaRepository.findById(id);
    }
}
