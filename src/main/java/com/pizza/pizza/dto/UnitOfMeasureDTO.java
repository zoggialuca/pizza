package com.pizza.pizza.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "unitOfMeasureList", itemRelation = "unitOfMeasure")
public class UnitOfMeasureDTO extends RepresentationModel<UnitOfMeasureDTO> {

  private Long id;

  @NotBlank
  private String name;
}
