package com.pizza.pizza.dto;

import com.pizza.pizza.model.Pizza;
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
@Relation(collectionRelation = "pizzaList", itemRelation = "pizza")
public class PizzaDTO extends RepresentationModel<PizzaDTO> {

  private Long id;

  @NotBlank
  private String name;

  private boolean isVegetarian;

  public Pizza toEntity() {
    return Pizza.builder()
                .id(id)
                .name(name)
                .isVegetarian(isVegetarian)
                .build();
  }
}
