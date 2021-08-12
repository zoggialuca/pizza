package com.pizza.pizza.service;

import com.pizza.pizza.converter.Converter;
import com.pizza.pizza.dto.UnitOfMeasureDTO;
import com.pizza.pizza.exception.UnitOfMeasureAlreadyExistsException;
import com.pizza.pizza.exception.UnitOfMeasureNotFoundException;
import com.pizza.pizza.model.UnitOfMeasure;
import com.pizza.pizza.repository.UnitOfMeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnitOfMeasureService {
    private final Converter<UnitOfMeasure, UnitOfMeasureDTO> converter;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public List<UnitOfMeasureDTO> findAll() {
        return unitOfMeasureRepository.findAll().stream().map(converter::toDTO)
                .collect(Collectors.toList());
    }

    public UnitOfMeasureDTO findById(Long id) {
        return unitOfMeasureRepository.findById(id).map(converter::toDTO)
                .orElseThrow(() -> new UnitOfMeasureNotFoundException(id));
    }

    public UnitOfMeasureDTO findByName(String name) {
        return unitOfMeasureRepository.findByName(name).map(converter::toDTO)
                .orElseThrow(() -> new UnitOfMeasureNotFoundException(name));
    }

    public UnitOfMeasureDTO create(UnitOfMeasureDTO unitOfMeasureDTO) {
        var unitOfMeasure = converter.toOptionalEntity(unitOfMeasureDTO);
        return unitOfMeasure.map(unitOfMeasureRepository::save).map(converter::toDTO).orElse(null);
    }

    public UnitOfMeasureDTO update(UnitOfMeasureDTO unitOfMeasureDTO, Long id) {
        if (unitOfMeasureRepository.findByName(unitOfMeasureDTO.getName()).isPresent()) {
            throw new UnitOfMeasureAlreadyExistsException(unitOfMeasureDTO.getName());
        }
        return unitOfMeasureRepository.findById(id)
                .map(unitOfMeasure -> {
                    unitOfMeasure.setName(unitOfMeasureDTO.getName());
                    return unitOfMeasureRepository.save(unitOfMeasure);
                })
                .map(converter::toDTO)
                .orElseThrow(() -> new UnitOfMeasureNotFoundException(id));
    }

    public void delete(Long id) {
        unitOfMeasureRepository.deleteById(id);
    }
}
