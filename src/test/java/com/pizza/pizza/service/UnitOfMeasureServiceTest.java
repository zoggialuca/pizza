package com.pizza.pizza.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pizza.pizza.converter.EntityDTOBidirectionalConverter;
import com.pizza.pizza.dto.UnitOfMeasureDTO;
import com.pizza.pizza.exception.UnitOfMeasureAlreadyExistsException;
import com.pizza.pizza.exception.UnitOfMeasureNotFoundException;
import com.pizza.pizza.model.UnitOfMeasure;
import com.pizza.pizza.repository.UnitOfMeasureRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceTest {

  private static final String UNIT_OF_MEASURE_NAME = "GRAM";
  private static final Long ID = 1L;

  @Mock
  private EntityDTOBidirectionalConverter<UnitOfMeasure, UnitOfMeasureDTO> converter;
  @Mock
  private UnitOfMeasureRepository unitOfMeasureRepository;

  private UnitOfMeasureService unitOfMeasureService;

  @BeforeEach
  public void setUp() {
    unitOfMeasureService = new UnitOfMeasureService(converter, unitOfMeasureRepository);
  }

  @Test
  void shouldThrowUnitOfMeasureAlreadyExistsException_update() {
    UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();
    unitOfMeasureDTO.setName(UNIT_OF_MEASURE_NAME);

    when(unitOfMeasureRepository.findByName(UNIT_OF_MEASURE_NAME)).thenReturn(Optional.of(new UnitOfMeasure(UNIT_OF_MEASURE_NAME)));

    assertThatExceptionOfType(UnitOfMeasureAlreadyExistsException.class).isThrownBy(() -> unitOfMeasureService.update(unitOfMeasureDTO, ID));
  }

  @Test
  void shouldThrowUnitOfMeasureNotFoundException_update() {
    UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();
    unitOfMeasureDTO.setName(UNIT_OF_MEASURE_NAME);

    UnitOfMeasure unitOfMeasure = Mockito.mock(UnitOfMeasure.class);
    when(unitOfMeasureRepository.findByName(UNIT_OF_MEASURE_NAME)).thenReturn(Optional.empty());
    when(unitOfMeasureRepository.findById(ID)).thenReturn(Optional.empty());

    assertThatExceptionOfType(UnitOfMeasureNotFoundException.class).isThrownBy(() -> unitOfMeasureService.update(unitOfMeasureDTO, ID));
  }

  @Test
  void shouldReturnUnitOfMeasureDTO_update() {
    UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();
    unitOfMeasureDTO.setName(UNIT_OF_MEASURE_NAME);

    UnitOfMeasure unitOfMeasure = Mockito.mock(UnitOfMeasure.class);
    when(unitOfMeasureRepository.findByName(UNIT_OF_MEASURE_NAME)).thenReturn(Optional.empty());
    when(unitOfMeasureRepository.findById(ID)).thenReturn(Optional.of(unitOfMeasure));
    when(unitOfMeasureRepository.save(Mockito.any(UnitOfMeasure.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
    when(converter.toDTO(unitOfMeasure)).thenReturn(unitOfMeasureDTO);
    unitOfMeasureService.update(unitOfMeasureDTO, ID);
    verify(unitOfMeasure).setName(unitOfMeasureDTO.getName());

  }

  @Test
  public void shouldThrowUnitOfMeasureNotFoundException_findByName() {
    when(unitOfMeasureRepository.findByName(UNIT_OF_MEASURE_NAME)).thenReturn(Optional.empty());
    assertThatExceptionOfType(UnitOfMeasureNotFoundException.class).isThrownBy(() -> unitOfMeasureService.findByName(UNIT_OF_MEASURE_NAME));
  }

  @Test
  public void shouldReturnUnitOfMeasureDTO_findByName() {
    UnitOfMeasure unitOfMeasure = new UnitOfMeasure(UNIT_OF_MEASURE_NAME);
    UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();
    unitOfMeasureDTO.setName(UNIT_OF_MEASURE_NAME);
    when(unitOfMeasureRepository.findByName(UNIT_OF_MEASURE_NAME)).thenReturn(Optional.of(unitOfMeasure));
    when(converter.toDTO(unitOfMeasure)).thenReturn(unitOfMeasureDTO);

    var result = unitOfMeasureService.findByName(UNIT_OF_MEASURE_NAME);
    assertThat(result).isEqualTo(unitOfMeasureDTO);

  }

  @Test
  public void shouldThrowUnitOfMeasureNotFoundException_findById() {
    when(unitOfMeasureRepository.findById(ID)).thenReturn(Optional.empty());
    assertThatExceptionOfType(UnitOfMeasureNotFoundException.class).isThrownBy(() -> unitOfMeasureService.findById(ID));
  }

  @Test
  public void shouldReturnUnitOfMeasureDTO_findById() {
    UnitOfMeasure unitOfMeasure = new UnitOfMeasure(UNIT_OF_MEASURE_NAME);
    UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();
    unitOfMeasureDTO.setName(UNIT_OF_MEASURE_NAME);
    when(unitOfMeasureRepository.findById(ID)).thenReturn(Optional.of(unitOfMeasure));
    when(converter.toDTO(unitOfMeasure)).thenReturn(unitOfMeasureDTO);

    var result = unitOfMeasureService.findById(ID);
    assertThat(result).isEqualTo(unitOfMeasureDTO);

  }

  @Test
  public void shouldReturnEmptyList_findAll() {
    when(unitOfMeasureRepository.findAll()).thenReturn(Collections.emptyList());

    List<UnitOfMeasureDTO> result = unitOfMeasureService.findAll();

    assertThat(result).hasSize(0);
  }
}