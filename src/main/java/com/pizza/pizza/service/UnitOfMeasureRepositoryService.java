package com.pizza.pizza.service;

import java.util.Optional;

import com.pizza.pizza.model.UnitOfMeasure;
import com.pizza.pizza.repository.UnitOfMeasureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitOfMeasureRepositoryService extends RepositoryService<UnitOfMeasure, Long, UnitOfMeasureRepository>{
    
    @Autowired UnitOfMeasureRepository unitOfMeasureRepository;
    
    public Optional<UnitOfMeasure> findByName(String name){
        return unitOfMeasureRepository.findByName(name);
    }
}
